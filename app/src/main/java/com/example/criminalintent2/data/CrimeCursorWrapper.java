package com.example.criminalintent2.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.criminalintent2.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Col.UUID));
        String title = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Col.TITLE));
        long date = getLong(getColumnIndex(CrimeDBSchema.CrimeTable.Col.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Col.SOLVED));
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        return crime;
    }

}
