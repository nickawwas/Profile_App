package com.example.consultation_app.database;

// DO SQLite DB Here
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.consultation_app.database.dao.EventDAO;
//import com.example.consultation_app.database.entity.Event;
//
//@Database(entities = { Event.class }, version = 2)
//public abstract class AppDB extends RoomDatabase {
//    private static volatile AppDB instance;
//    private static final String DB_NAME = "eventDB";
//
//    protected AppDB(){};
//
//    // Create DB Function
//    private static AppDB create(Context context) {
//        return Room.databaseBuilder(context, AppDB.class, DB_NAME).allowMainThreadQueries().build();
//    }
//
//    // Singleton Creating DB or Returning Existing DB
//    public static synchronized AppDB getInstance(Context context) {
//        if(instance == null)
//            instance = create(context);
//
//        return instance;
//    }
//
//    // Give Access to DAO
//    public abstract EventDAO eventDAO();
//}
