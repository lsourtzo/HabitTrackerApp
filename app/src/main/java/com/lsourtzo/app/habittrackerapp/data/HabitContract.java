package com.lsourtzo.app.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habit tracker database table.
     * Each entry in the table represents a single Habit event.
     */
    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "htracker";

        /**
         * Unique ID number for the Habit event (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the Habit event.
         *
         * Type: TEXT
         *
         * here USER can named by himself what habit they want to track
         * can be any text they want e.g. drink water, walk 20 min, read a book or any ...
         *
         */
        public final static String COLUMN_H_NAME ="name";

        /**
         * Status of the Habit event.
         *
         * Type: INTEGER
         *
         * In this filed can select from 3 default status types bellow
         * Complete, Incomplete, Unknown
         *
         */
        public final static String COLUMN_H_STATUS = "status";

       /**
         * Date of the Habit event.
         *
         * Type: TEXT
         */
        public final static String COLUMN_H_DATE = "date";


        /**
         * Possible values for the Status of the Habit event.
         */
        public static final int STATUS_UNKNOWN = 0;
        public static final int STATUS_COMPLETE = 1;
        public static final int STATUS_INCOMPLETE = 2;

    }

}

