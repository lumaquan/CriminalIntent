package com.example.criminalintent2;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Map<UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            Crime crime = new Crime();
            crime.setTitle("CRIME # " + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.put(crime.getId(), crime);
        }
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrimeUUID(UUID id) {
        return mCrimes.get(id);
    }

    public int position(UUID id) {
        return getCrimes().indexOf(getCrimeUUID(id));
    }
}
