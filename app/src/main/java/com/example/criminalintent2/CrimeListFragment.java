package com.example.criminalintent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.criminalintent2.Lifecycle.LoggingLifecycleFragment;
import com.example.criminalintent2.data.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends LoggingLifecycleFragment implements CrimeAdapter.OnItemClickedListener, CrimeLab.Callback {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView crimeList;
    private CrimeAdapter crimeAdapter;
    private boolean mSubtitleVisible;
    private LinearLayout noCrimesContainer;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list_crime, container, false);
        crimeList = view.findViewById(R.id.recyclerview_crime_list);
        crimeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        crimeAdapter = new CrimeAdapter(null, this);
        crimeList.setAdapter(crimeAdapter);
        crimeList.addItemDecoration(new ItemDivider(getActivity()));
        noCrimesContainer = view.findViewById(R.id.no_crimes_container);
        progressBar = view.findViewById(R.id.progress_bar);

        Button addCrimeButton = view.findViewById(R.id.add_crime_button);
        addCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCrimeGoPager();
            }
        });

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        showLoading(true);
        CrimeLab.get(getActivity()).enqueue(this);
    }

    @Override
    public void results(List<Crime> crimes) {
        showLoading(false);
        crimeAdapter.setCrimes(crimes);
        if (crimes.size() > 0) {
            noCrimesContainer.setVisibility(View.INVISIBLE);
            crimeList.setVisibility(View.VISIBLE);
        } else {
            noCrimesContainer.setVisibility(View.VISIBLE);
            crimeList.setVisibility(View.INVISIBLE);
        }
        updateSubtitle();
    }

    private void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        noCrimesContainer.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
        crimeList.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public void onItemClick(UUID uuid) {
        startActivity(CrimePagerActivity.newIntent(getActivity(), uuid));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_crime_list, menu);
        MenuItem menuItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            menuItem.setTitle(R.string.hide_subtitle);
        } else {
            menuItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newCrime:
                addCrimeGoPager();
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addCrimeGoPager() {
        Crime crime = new Crime();
        CrimeLab.get(getActivity()).addCrime(crime);
        Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
        startActivity(intent);
    }

    private void updateSubtitle() {
        int numberCrimes = CrimeLab.get(getActivity()).getCrimes().size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, numberCrimes, numberCrimes);
        if (!mSubtitleVisible) {
            subtitle = null;
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(subtitle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }


}

