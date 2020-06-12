package com.mrspd.moviesappstage1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mrspd.moviesappstage1.model.Result;
import com.mrspd.moviesappstage1.repository.MovieRepository;

import java.util.List;
/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public class MovieViewModel extends AndroidViewModel {
    private LiveData<List<Result>> myFavoriteMovies;
    private MovieRepository movieRepository;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        myFavoriteMovies = movieRepository.getAllmyFavoriteMoviesFromRoomDB();

    }


    public boolean isFavorite(int movie_id) {
        return movieRepository.CheckIsFavorite(movie_id);
    }
    public void deleteThisMovie(Result result) {
        movieRepository.deleteMyMovie(result);
    }

    public void addrthisMovie(Result result) {
        movieRepository.addThistoFavorite(result);
    }

    public LiveData<List<Result>> getFavoriteMovies() {
        return myFavoriteMovies;
    }

}
