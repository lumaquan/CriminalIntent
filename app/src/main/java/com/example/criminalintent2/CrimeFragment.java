package com.example.criminalintent2;


import android.app.Activity;
import android.content.Intent;
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

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends LoggingLifecycleFragment {
    private Crime mCrime;
    private EditText titleField;
    private CheckBox solvedCheckBox;
    private Button dateButton;

    private static final String CRIME_ID_EXTRA = "crime_id_extra";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    public static CrimeFragment newInstance(UUID uuid) {
        CrimeFragment crimeFragment = new CrimeFragment();
        Bundle args = new Bundle();
        args.putSerializable(CRIME_ID_EXTRA, uuid);
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    public CrimeFragment() {
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
        dateButton.setEnabled(true);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(mCrime.getDate());
                dialog.show(getFragmentManager(), DIALOG_DATE);
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            }
        });

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerDialog.DATE_EXTRA);
            mCrime.setDate(date);
            dateButton.setText(mCrime.getDate().toString());
        }
    }
}
