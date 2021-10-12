package com.example.consultation_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.consultation_app.models.Profile;
import com.example.consultation_app.models.Access;

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
        //TODO: Change Format from Text to Date

        // Create Profile and Access Tables
        String CREATE_PROFILE_TABLE = String.format(
                "CREATE TABLE " + Config.PROFILE_TABLE_NAME + " (" +
                        Config.PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Config.PROFILE_COLUMN_SURNAME + " TEXT NOT NULL, " +
                        Config.PROFILE_COLUMN_NAME + " TEXT NOT NULL, " +
                        Config.PROFILE_COLUMN_SID + " INTEGER NOT NULL, " +
                        Config.PROFILE_COLUMN_GPA + " REAL NOT NULL);"
        );
        db.execSQL(CREATE_PROFILE_TABLE);


        String CREATE_ACCESS_TABLE = String.format(
                "CREATE TABLE " + Config.ACCESS_TABLE_NAME + " (" +
                        Config.ACCESS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Config.ACCESS_COLUMN_PID + " INTEGER NOT NULL, " +
                        "FOREIGN KEY(" + Config.ACCESS_COLUMN_PID + " REFERENCES " + Config.PROFILE_TABLE_NAME + "(" + Config.PROFILE_COLUMN_ID + ")," +
                        Config.ACCESS_COLUMN_TYPE + " TEXT NOT NULL, " +
                        Config.ACCESS_COLUMN_TIME + " TEXT NOT NULL);"
        );

        db.execSQL(CREATE_ACCESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop Both Profile and Access Tables
        db.execSQL("DROP TABLE IF EXISTS " + Config.PROFILE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Config.PROFILE_TABLE_NAME);

        // Create New Version of Tables
        onCreate(db);
    }

    public long insertProfile(Profile profile) {
        long id = -1;

        String INSERT_PROFILE_TABLE = String.format(
            "INSERT INTO " + Config.PROFILE_TABLE_NAME + " VALUES (" +
                    profile.getSurname() + ", " +
                    profile.getStudentName() + ", " +
                    profile.getStudentId() + ", " +
                    profile.getGpa() + ");"
        );
        // profile.getPID() + ", " +

        SQLiteDatabase db = this.getWritableDatabase();

        // Try Insert Into Table
        try {
            db.execSQL(INSERT_PROFILE_TABLE);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed Insert into Profile Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        //Otherwise, Do This
        // ContentValues contentValues = new ContentValues();
        // contentValues.put(Config.PROFILE_COLUMN_SURNAME, profile.getSurname());
        // contentValues.put(Config.PROFILE_COLUMN_NAME, profile.getStudentName());
        // contentValues.put(Config.PROFILE_COLUMN_SID, profile.getSurname());
        // contentValues.put(Config.PROFILE_COLUMN_GPA, profile.getStudentName());
        //id = db.insertOrThrow(Config.PROFILE_TABLE_NAME, null, contentValues);

        return id;
    }

    public long insertAccess(Access access) {
        long id = -1;

        String INSERT_ACCESS_ENTRY = String.format(
            "INSERT INTO " + Config.ACCESS_TABLE_NAME + " VALUES (" +
                    access.getProfileID() + ", " +
                    access.getAccessType() + ", " +
                    access.getTimeStamp() + ");"
        );
        // access.getID() + ", " +

        SQLiteDatabase db = this.getWritableDatabase();

        // Try Insert Into Table
        try {
            db.execSQL(INSERT_ACCESS_ENTRY);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed Insert into Access Table: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        //Otherwise, Do This
        // ContentValues contentValues = new ContentValues();
        // contentValues.put(Config.ACCESS_COLUMN_PID, profile.getProfileID());
        // contentValues.put(Config.ACCESS_COLUMN_TYPE, profile.getAccessType());
        // contentValues.put(Config.ACCESS_COLUMN_TIME, profile.getTimeStamp());
        //id = db.insertOrThrow(Config.ACCESS_TABLE_NAME, null, contentValues);

        return id;
    }

    public List<Profile> getAllProfiles() {
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor cursor = null;
//        List<Course> courseList = new ArrayList<>();

//        try {
//            cursor = db.query(Config.TABLE_COURSE, null, null, null, null, null, null);
//
//            if (cursor != null){
//                if(cursor.moveToFirst()){
//                    do {
//                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
//                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
//                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));
//
//                        courseList.add(new Course(id,title,code));
//                    } while(cursor.moveToNext());
//
//                    return courseList;
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(mContext, "Operation failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
//        } finally {
//            if(cursor != null)
//                cursor.close();
//            db.close();
//        }
        return Collections.emptyList();
    }

    public List<Access> getAllAccesses(long profileId) {
        SQLiteDatabase db = this.getReadableDatabase();

        // From Profile Id Get All Accesses

//        Cursor cursor = null;
//        List<Course> courseList = new ArrayList<>();

//        try {
//            cursor = db.query(Config.TABLE_COURSE, null, null, null, null, null, null);
//
//            if (cursor != null){
//                if(cursor.moveToFirst()){
//                    do {
//                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID));
//                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE));
//                        String code = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COURSE_CODE));
//
//                        courseList.add(new Course(id,title,code));
//                    } while(cursor.moveToNext());
//
//                    return courseList;
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(mContext, "Operation failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
//        } finally {
//            if(cursor != null)
//                cursor.close();
//            db.close();
//        }
        return Collections.emptyList();
    }

    // TODO: Set Time Format yyyy-mm-dddd @ hh:mm:ss

    // TODO: Delete Entry from Profile Table
    // Doesn't Delete Access Table Entry, Adds Deleted Access
    public void deleteProfile(long profileId) {

    }
}