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
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrimeUUID(UUID id) {
        return mCrimes.get(id);
    }

    public void addCrime(Crime c) {
        mCrimes.put(c.getId(), c);
    }

    public int position(UUID id) {
        return getCrimes().indexOf(getCrimeUUID(id));
    }
}
