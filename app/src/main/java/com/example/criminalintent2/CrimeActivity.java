package com.example.criminalintent2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String CRIME_ID_EXTRA = "crime_id_extra";

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(CRIME_ID_EXTRA, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return CrimeFragment.newInstance((UUID) getIntent().getSerializableExtra(CRIME_ID_EXTRA));
    }
}
