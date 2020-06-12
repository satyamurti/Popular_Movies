package com.mrspd.moviesappstage1.db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mrspd.moviesappstage1.model.Result;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Result result);

    @Query("SELECT  * FROM movies")
    LiveData<List<Result>> getMyMovies();

    @Query("SELECT * FROM movies WHERE id= :movie_id")
    boolean checkWetherThisMOvieIsFavorite(int movie_id);

    @Delete
    void deleteThisMovie(Result result);
}
