package com.example.criminalintent2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
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
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(NAME, null, values);
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
