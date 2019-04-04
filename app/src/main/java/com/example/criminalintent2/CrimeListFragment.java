package com.example.criminalintent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.criminalintent2.Lifecycle.LoggingLifecycleFragment;

import java.util.UUID;

public class CrimeListFragment extends LoggingLifecycleFragment implements CrimeAdapter.OnItemClickedListener {

    private RecyclerView crimeList;
    private CrimeAdapter crimeAdapter;

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
        updateUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        crimeAdapter.setCrimes(CrimeLab.get(getActivity()).getCrimes());
    }

    @Override
    public void onItemClick(UUID uuid) {
        startActivity(CrimePagerActivity.newIntent(getActivity(), uuid));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_crime_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newCrime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

