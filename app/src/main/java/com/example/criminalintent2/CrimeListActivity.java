package com.example.criminalintent2;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment getFragment() {
        return new CrimeListFragment();
    }
}
