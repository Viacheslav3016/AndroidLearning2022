package com.example.mylovefilms;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabBase extends RoomDatabase {

    private static final String NAME_DB = "movieDB.db";
    public static MovieDatabBase instance = null;

    public static MovieDatabBase getInstance(Application application) {
        if (instance == null){
            instance = Room.databaseBuilder(application, MovieDatabBase.class, NAME_DB ).build();
        }
        return instance;
    }

    abstract MovieDao movieDao();
}
