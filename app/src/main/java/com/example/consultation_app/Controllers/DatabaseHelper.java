package com.example.consultation_app.Controllers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.consultation_app.Models.Profile;
import com.example.consultation_app.Models.Access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    // DB Helper Constructor
    public DatabaseHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Profile and Access Tables
        String CREATE_PROFILE_TABLE = String.format(
                "CREATE TABLE " + Config.PROFILE_TABLE_NAME + " (" +
                        Config.PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        Config.PROFILE_COLUMN_SURNAME + " TEXT NOT NULL, " +
                        Config.PROFILE_COLUMN_NAME + " TEXT NOT NULL, " +
                        Config.PROFILE_COLUMN_GPA + " REAL NOT NULL, " +
                        Config.PROFILE_COLUMN_CREATION_DATE + " TEXT NOT NULL);"
        );
        db.execSQL(CREATE_PROFILE_TABLE);

        String CREATE_ACCESS_TABLE = String.format(
                "CREATE TABLE " + Config.ACCESS_TABLE_NAME + " (" +
                        Config.ACCESS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Config.ACCESS_COLUMN_PID + " INTEGER NOT NULL, " +
                        Config.ACCESS_COLUMN_TYPE + " TEXT NOT NULL, " +
                        Config.ACCESS_COLUMN_TIME + " TEXT NOT NULL);"
        ); // "FOREIGN KEY(" + Config.ACCESS_COLUMN_PID + ") REFERENCES " + Config.PROFILE_TABLE_NAME + "(" + Config.PROFILE_COLUMN_ID + ")," +
        db.execSQL(CREATE_ACCESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop Both Profile and Access Tables
        db.execSQL("DROP TABLE IF EXISTS " + Config.PROFILE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Config.ACCESS_TABLE_NAME);

        // Create New Version of Tables
        onCreate(db);
    }

    // Insert Profile into Profile Table
    // SQL Query: "INSERT INTO " + Config.PROFILE_TABLE_NAME + " VALUES (" + profile.getSurname() + ", " + profile.getStudentName() + ", " + profile.getStudentId() + ", " + profile.getGpa() + ");"
    public long insertProfile(Profile profile) {
        long id = -1;

        // Get Writable Database Instance
        SQLiteDatabase db = this.getWritableDatabase();

        // Set Content Data to Profile Entry
        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.PROFILE_COLUMN_ID, profile.getProfileId());
        contentValues.put(Config.PROFILE_COLUMN_SURNAME, profile.getSurname());
        contentValues.put(Config.PROFILE_COLUMN_NAME, profile.getStudentName());
        contentValues.put(Config.PROFILE_COLUMN_GPA, profile.getGpa());
        contentValues.put(Config.PROFILE_COLUMN_CREATION_DATE, profile.getCreationDate());

        // Insert Entry into Profile Table
        try {
            id = db.insertOrThrow(Config.PROFILE_TABLE_NAME, null, contentValues);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed Insert into Profile Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return id;
    }

    // Insert Access into Access Table
    // SQL Query: "INSERT INTO " + Config.ACCESS_TABLE_NAME + " VALUES (" + access.getProfileID() + ", " +  access.getAccessType() + ", " + access.getTimeStamp() + ");"
    public long insertAccess(Access access) {
        long id = -1;

        // Get Writable Database Instance
        SQLiteDatabase db = this.getWritableDatabase();

        // Set Content Data to Access Entry
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.ACCESS_COLUMN_PID, access.getProfileID());
        contentValues.put(Config.ACCESS_COLUMN_TYPE, access.getAccessType());
        contentValues.put(Config.ACCESS_COLUMN_TIME, access.getTimeStamp());

        // Insert Entry into Access Table
        try {
            id = db.insertOrThrow(Config.ACCESS_TABLE_NAME, null, contentValues);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed Insert into Access Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return id;
    }

    // Get All Profiles Sorted By Name or Id to Display on Main Activity
    // SQL Query: "SELECT * FROM " + Config.PROFILE_TABLE_NAME + " ORDER BY " + orderType + ";"
    public List<Profile> getAllProfiles(boolean orderByName) {
        String orderType = orderByName ? Config.PROFILE_COLUMN_SURNAME : Config.PROFILE_COLUMN_ID;

        // Create Readable Database Instance
        SQLiteDatabase db = this.getReadableDatabase();

        // Define Cursor and Profile List
        Cursor cursor = null;
        List<Profile> profileList = new ArrayList<>();

        try {
            cursor = db.query(Config.PROFILE_TABLE_NAME, null, null, null, null, null, orderType);

            if(cursor != null && cursor.moveToFirst()) {
                do {
                    // Add to Profile List
                    profileList.add(getProfile(cursor));

                // Move Cursor to Next Element in Table
                } while(cursor.moveToNext());

                return profileList;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Failed to Retrieve Data from Profile Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor != null)
                cursor.close();

            db.close();
        }

        return Collections.emptyList();
    }

    // Get Specific Profiles Data to Display on Specific Profile Activity
    // SQL Query: "SELECT * FROM " + Config.PROFILE_TABLE_NAME + " WHERE " + Config.PROFILE_COLUMN_ID + " = " + profileId + ";"
    public Profile getProfileById(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        Profile profile = null;

        try {
            cursor = db.query(Config.PROFILE_TABLE_NAME, null, Config.PROFILE_COLUMN_ID + "=?", new String[]{ "" + studentId }, null, null, null);

            // Return First Profile Object Found with Id, Else Null
            if(cursor != null && cursor.moveToFirst()) {
                return getProfile(cursor);
            }
        } catch(Exception e) {
            Toast.makeText(context, "Failed to Retrieve Selected Profile from Profile Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor != null)
                cursor.close();

            db.close();
        }

        return null;
    }

    // Get All Accesses Given a Profile Id to Display on Specific Profile Activity
    // SQL Query: "SELECT * FROM " + Config.ACCESS_TABLE_NAME + " WHERE " + Config.ACCESS_COLUMN_PID + " = " + profileId + ";"
    public List<Access> getAllAccessesById(long profileId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        List<Access> accessList = new ArrayList<>();

        // Get All Entries from Access Table Where PID = profileId Parameter
        try {
            cursor = db.query(Config.ACCESS_TABLE_NAME, null, Config.ACCESS_COLUMN_PID + "=?", new String[]{ "" + profileId }, null, null, Config.ACCESS_COLUMN_ID + " DESC");

            if(cursor != null && cursor.moveToFirst()) {
                do {
                    // Add to Access List
                    accessList.add(getAccess(cursor));

                // Move Cursor to Next Element in Table
                } while(cursor.moveToNext());

                return accessList;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Failed to Retrieve Data from Access Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor != null)
                cursor.close();

            db.close();
        }

        return Collections.emptyList();
    }

    // Delete Entry from Profile Table and Add Deleted Access to Access Table
    // SQL Query: "DELETE * FROM " + Config.ACCESS_TABLE_NAME + " WHERE " + Config.ACCESS_COLUMN_PID + " = " + profileId + ";"
    public long deleteProfile(int profileId) {
        long id = -1;

        // Create Writable Database Instance to Delete
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete Entry from Profile Table
        try {
            id = db.delete(Config.PROFILE_TABLE_NAME, Config.PROFILE_COLUMN_ID + " =?", new String[]{ "" + profileId});
        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed Insert into Access Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return id;
    }

    // Helper Methods to Get Profile and Access from Cursor
    @SuppressLint("Range")
    public Profile getProfile(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(Config.PROFILE_COLUMN_ID));
        String surname = cursor.getString(cursor.getColumnIndex(Config.PROFILE_COLUMN_SURNAME));
        String name = cursor.getString(cursor.getColumnIndex(Config.PROFILE_COLUMN_NAME));
        double gpa = cursor.getDouble(cursor.getColumnIndex(Config.PROFILE_COLUMN_GPA));
        String creationDate = cursor.getString(cursor.getColumnIndex(Config.PROFILE_COLUMN_CREATION_DATE));

        return new Profile(id, surname, name, gpa, creationDate);
    }

    @SuppressLint("Range")
    public Access getAccess(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(Config.ACCESS_COLUMN_ID));
        int pid = cursor.getInt(cursor.getColumnIndex(Config.ACCESS_COLUMN_PID));
        String type = cursor.getString(cursor.getColumnIndex(Config.ACCESS_COLUMN_TYPE));
        String timeStamp = cursor.getString(cursor.getColumnIndex(Config.ACCESS_COLUMN_TIME));

        return new Access(id, pid, type, timeStamp);
    }
}