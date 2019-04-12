package com.example.criminalintent2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.example.criminalintent2.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.DATE;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.SOLVED;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.TITLE;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.NAME;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private List<Crime> cachedCrimes = new ArrayList<>();
    private boolean cacheDirty = true;

    public interface Callback {
        void results(List<Crime> crimes);
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        if (cachedCrimes != null && !cacheDirty) return cachedCrimes;
        cachedCrimes.clear();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cachedCrimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        cacheDirty = false;
        return cachedCrimes;
    }

    public void enqueue(Callback callback) {
        if (cachedCrimes != null && !cacheDirty) {
            callback.results(cachedCrimes);
            return;
        }
        cachedCrimes.clear();
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() ->
        {
            CrimeCursorWrapper cursor = queryCrimes(null, null);
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    cachedCrimes.add(cursor.getCrime());
                    cursor.moveToNext();
                }

                handler.postDelayed(() -> {
                    callback.results(cachedCrimes);
                }, 2000);

            } finally {
                cursor.close();
            }


        }).start();
    }


    public Crime getCrimeUUID(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(CrimeDBSchema.CrimeTable.Col.UUID + " = ?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void deleteCrime(UUID id) {
        mDatabase.delete(NAME, CrimeDBSchema.CrimeTable.Col.UUID + " = ?", new String[]{id.toString()});
        cacheDirty = true;
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(NAME, null, values);
        cacheDirty = true;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDBSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new CrimeCursorWrapper(cursor);
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(NAME, values, CrimeDBSchema.CrimeTable.Col.UUID + " = ?", new String[]{uuidString});
        cacheDirty = true;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDBSchema.CrimeTable.Col.UUID, crime.getId().toString());
        values.put(TITLE, crime.getTitle());
        values.put(DATE, crime.getDate().getTime());
        values.put(SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }

}
