package com.example.consultation_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    // DB Helper Constructor
    public DatabaseHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Change Format from Text to Integer and Date

        // Create Profile and Access Tables
        String CREATE_PROFILE_TABLE = String.format(
            "CREATE TABLE " + Config.PROFILE_TABLE_NAME + " (" +
                    Config.PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Config.PROFILE_COLUMN_SURNAME + " TEXT NOT NULL, " +
                    Config.PROFILE_COLUMN_NAME + " TEXT NOT NULL, " +
                    Config.PROFILE_COLUMN_SID + " TEXT NOT NULL, " +
                    Config.PROFILE_COLUMN_GPA + " TEXT NOT NULL)"
            );
        db.execSQL(CREATE_PROFILE_TABLE);


        String CREATE_ACCESS_TABLE = String.format(
            "CREATE TABLE " + Config.ACCESS_TABLE_NAME + " (" +
                    Config.ACCESS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Config.ACCESS_COLUMN_PID + " INTEGER NOT NULL, " +
                    Config.ACCESS_COLUMN_TYPE + " TEXT NOT NULL, " +
                    Config.ACCESS_COLUMN_TIME + " TEXT NOT NULL)"
            );

        db.execSQL(CREATE_ACCESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    String INSERT_ACCESS_ENTRY = String.format(
//            "INSERT INTO " + Config.ACCESS_TABLE_NAME + " VALUES (" + ")"
//    );
//
//    String UPDATE_ACCESS_ENTRY = String.format(
//            "UPDATE " + Config.ACCESS_TABLE_NAME
//    );
}