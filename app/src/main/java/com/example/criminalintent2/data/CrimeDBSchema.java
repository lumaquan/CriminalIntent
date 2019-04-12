package com.example.criminalintent2.data;

public class CrimeDBSchema {

    public static class CrimeTable{

        public  static final String NAME = "crimes";

        public static final class Col{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String SUSPECT_ID = "suspect_id";
        }
    }

}
