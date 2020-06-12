package com.mrspd.moviesappstage1.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mrspd.moviesappstage1.MyExcecutors;
import com.mrspd.moviesappstage1.db.Dao;
import com.mrspd.moviesappstage1.db.MovieDB;
import com.mrspd.moviesappstage1.model.Result;

import java.util.List;

public class MovieRepository {
    private Dao dao;
    private LiveData<List<Result>> movies;
    private MyExcecutors excecutors;

    public MovieRepository(Application application) {
        dao = MovieDB.getMoviewInstanceFromRoom(application).myDAo();
        movies = dao.getMyMovies();
        excecutors = MyExcecutors.getInstance();
    }

    public LiveData<List<Result>> getAllmyFavoriteMoviesFromRoomDB() {
        return movies;
    }

    public void addThistoFavorite(final Result result) {
        excecutors.diskIO().execute(() ->{
            dao.insert(result);
        });

    }
    public boolean CheckIsFavorite(int movie_id) {
        return dao.checkWetherThisMOvieIsFavorite(movie_id);
    }

    public void deleteMyMovie(Result result) {
        excecutors.diskIO().execute(() ->{
            dao.deleteThisMovie(result);
        });

    }
}
