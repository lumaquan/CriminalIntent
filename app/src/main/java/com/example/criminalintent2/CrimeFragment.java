package com.example.criminalintent2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.criminalintent2.Lifecycle.LoggingLifecycleFragment;

import java.util.UUID;

public class CrimeFragment extends LoggingLifecycleFragment {
    private Crime mCrime;
    private EditText titleField;
    private CheckBox solvedCheckBox;
    private Button dateButton;

    private static final String CRIME_ID_EXTRA = "crime_id_extra";

    public static CrimeFragment newInstance(UUID uuid) {
        CrimeFragment crimeFragment = new CrimeFragment();
        Bundle args = new Bundle();
        args.putSerializable(CRIME_ID_EXTRA, uuid);
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    public CrimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        UUID uuid = (UUID) arguments.getSerializable(CRIME_ID_EXTRA);
        mCrime = CrimeLab.get(getActivity()).getCrimeUUID(uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        titleField = view.findViewById(R.id.crime_title);
        titleField.setText(mCrime.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dateButton = view.findViewById(R.id.crime_date);
        dateButton.setText(mCrime.getDate().toString());
        dateButton.setEnabled(false);

        solvedCheckBox = view.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(mCrime.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return view;
    }

}
