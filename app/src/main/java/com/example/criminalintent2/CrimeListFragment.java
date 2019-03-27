package com.example.criminalintent2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

public class CrimeListFragment extends Fragment implements CrimeAdapter.OnItemClickedListener {

    private static final String CRIME_POSITION_KEY = "crime_position_key";
    private RecyclerView crimeList;
    private CrimeAdapter crimeAdapter;
    private int crimePositionSelected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            crimePositionSelected = savedInstanceState.getInt(CRIME_POSITION_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_crime, container, false);
        crimeList = view.findViewById(R.id.recyclerview_crime_list);
        crimeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateUI() {
        if (crimeAdapter == null) {
            CrimeLab crimeLab = CrimeLab.get(getActivity());
            crimeAdapter = new CrimeAdapter(crimeLab.getCrimes(), this);
            crimeList.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyItemChanged(crimePositionSelected);
            crimeList.scrollToPosition(crimePositionSelected);
        }
    }

    @Override
    public void onItemClick(UUID uuid, int position) {
        crimePositionSelected = position;
        startActivity(CrimeActivity.newIntent(getActivity(), uuid));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CRIME_POSITION_KEY, crimePositionSelected);
    }


}

