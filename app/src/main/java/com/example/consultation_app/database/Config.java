package com.example.consultation_app.database;

public class Config {
    public static final String DB_NAME = "consultation_db";
    public static final int DB_VERSION = 1;

    public static final String PROFILE_TABLE_NAME = "profiles";
    public static final String PROFILE_COLUMN_ID = "_id";
    public static final String PROFILE_COLUMN_SURNAME = "surname";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_SID = "student_id"; // Remove
    public static final String PROFILE_COLUMN_GPA = "gpa";
    public static final String PROFILE_COLUMN_CREATION_DATE = "creation_date";

    public static final String ACCESS_TABLE_NAME = "accesses";
    public static final String ACCESS_COLUMN_ID = "_id";
    public static final String ACCESS_COLUMN_PID = "profile_id";
    public static final String ACCESS_COLUMN_TYPE = "type";
    public static final String ACCESS_COLUMN_TIME = "time";
}
