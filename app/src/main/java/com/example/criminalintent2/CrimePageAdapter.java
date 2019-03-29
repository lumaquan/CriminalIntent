package com.example.criminalintent2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

class CrimePageAdapter extends FragmentStatePagerAdapter {

    List<Crime> mCrimes;

    public CrimePageAdapter(FragmentManager fm, List<Crime> crimes) {
        super(fm);
        mCrimes = crimes;
    }

    @Override
    public Fragment getItem(int i) {
        CrimeFragment crimeFragment = CrimeFragment.newInstance(mCrimes.get(i).getId());
        return crimeFragment;
    }

    @Override
    public int getCount() {
        if (mCrimes == null) return 0;
        return mCrimes.size();
    }

}
