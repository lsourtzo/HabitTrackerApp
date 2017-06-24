package com.lsourtzo.app.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lsourtzo.app.habittrackerapp.data.HabitContract.HabitEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Database helper for Habit Tracker app. Manages database creation and version management.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habit_tracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Get current Date as default value in date column
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        // Create a String that contains the SQL statement to create the Habit table
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_H_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_H_STATUS + " INTEGER NOT NULL, "
                //set as default value the current date
                + HabitEntry.COLUMN_H_DATE + " TEXT NOT NULL DEFAULT '"+formattedDate+"');";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do
    }
}