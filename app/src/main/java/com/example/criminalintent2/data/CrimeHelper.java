package com.example.criminalintent2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.DATE;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.SOLVED;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.TITLE;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.Col.UUID;
import static com.example.criminalintent2.data.CrimeDBSchema.CrimeTable.NAME;

public class CrimeHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NAME + "(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UUID + ", " +
                TITLE + ", " +
                DATE + ", " +
                SOLVED +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
