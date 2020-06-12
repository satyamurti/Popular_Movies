package com.mrspd.moviesappstage1.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public class RetrofitInstance {
    private static Api api;
    private static final String BASEURL = "https://api.themoviedb.org";

    public static Api getMyMovies(Context context) {
        if (api == null) {

            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofit.create(Api.class);
        }

        return api;

    }
}
