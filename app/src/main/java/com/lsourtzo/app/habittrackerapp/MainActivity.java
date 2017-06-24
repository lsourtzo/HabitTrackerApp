package com.lsourtzo.app.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lsourtzo.app.habittrackerapp.data.HabitDbHelper;

import com.lsourtzo.app.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by lsourtzo on 24/06/2017.
 */

public class MainActivity  extends AppCompatActivity {
    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
    }

    /**
     * Helper method to INSERT hardcoded habits data into the database.
     */
    private void Data_Insertion() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create an insertion with some sample data in to the table
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_H_NAME, "Walk 20 min per day");
        values.put(HabitEntry.COLUMN_H_STATUS, HabitEntry.STATUS_UNKNOWN);
        values.put(HabitEntry.COLUMN_H_DATE, "12/05/2017");

        // Insert a new row for new habit in the database, returning the ID of that new row.
        // The first argument for db.insert() is the habit table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * Temporary helper method to return a Cursor object with data from the habit tracker database.
     */
    private Cursor Build_Cursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry.COLUMN_H_NAME,
                HabitEntry.COLUMN_H_STATUS,
                HabitEntry.COLUMN_H_DATE};

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        // Return the cursor which created from above query
        return cursor;
    }

    /**
     * Helper method to READ habits data from the database.
     */
    private void Data_Reading (){
        // Define the string where return the results of query
        // Build new String
        StringBuilder displayView = new StringBuilder(400);

        Cursor cursor = Build_Cursor();

        try {
            // Create a header in the Text View that looks like this:
            //
            // The Habits table contains <number of rows in Cursor> Habits.
            // name - status - date
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.append("The Habits table contains " + cursor.getCount() + " Habits.\n\n");
            displayView.append(HabitEntry.COLUMN_H_NAME + " - " +
                    HabitEntry.COLUMN_H_STATUS + " - " +
                    HabitEntry.COLUMN_H_DATE + "\n");

            // Figure out the index of each column
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_H_NAME);
            int statusColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_H_STATUS);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_H_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentName = cursor.getString(nameColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);
                String currentRecDate = cursor.getString(dateColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" +currentName + " - " +
                        currentStatus + " - " +
                        currentRecDate ));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
