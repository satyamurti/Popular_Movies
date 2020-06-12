package com.mrspd.moviesappstage1.api;

import com.mrspd.moviesappstage1.model.Moviwes2;
import com.mrspd.moviesappstage1.model.Reviews;
import com.mrspd.moviesappstage1.model.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public interface Api {
    @GET("3/movie/{id}/videos")
    Call<Trailers> getMyTrailer(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Call<Reviews> getMyReviews(@Path("id") int moveId, @Query("api_key") String apiKey, @Query("page") int page);
    @GET("3/movie/top_rated")
    Call<Moviwes2> getMyTopmovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("3/movie/popular")
    Call<Moviwes2> getMyPopulerMovies(@Query("page") int page, @Query("api_key") String apiKey);
}
