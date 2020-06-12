package com.mrspd.moviesappstage1.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mrspd.moviesappstage1.model.Result;

@Database(entities = {Result.class}, version = 2)
public abstract class MovieDB extends RoomDatabase {

    private static MovieDB instance;

    public static MovieDB getMoviewInstanceFromRoom(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDB.class, "moviewdb").build();
        }
        return instance;
    }

    public abstract Dao myDAo();

}
