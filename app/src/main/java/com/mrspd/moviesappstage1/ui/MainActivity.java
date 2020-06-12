package com.mrspd.moviesappstage1.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrspd.moviesappstage1.OnItemClickListener;
import com.mrspd.moviesappstage1.R;
import com.mrspd.moviesappstage1.adapters.Adapterr;
import com.mrspd.moviesappstage1.api.Api;
import com.mrspd.moviesappstage1.api.RetrofitInstance;
import com.mrspd.moviesappstage1.model.Moviwes2;
import com.mrspd.moviesappstage1.model.Result;
import com.mrspd.moviesappstage1.viewmodel.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;

    private static int SORT_MODE = 1;
    private static int PAGE = 1;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Adapterr listadapter;
    private List<Result> resultdatam;
    private Call<Moviwes2> mymovieisresultingcall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerview);
        progressBar = findViewById(R.id.loader);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        LoaddddMyPAgeee(PAGE);

    }

    private void LoaddddMyPAgeee(final int page) {
        Api api = RetrofitInstance.getMyMovies(this);
        if (SORT_MODE == 1) {
            mymovieisresultingcall = api.getMyPopulerMovies(page, this.getString(R.string.apiKey));
        } else {
            mymovieisresultingcall = api.getMyTopmovies(page, this.getString(R.string.apiKey));
        }

        mymovieisresultingcall.enqueue(new Callback<Moviwes2>() {
            @Override
            public void onResponse(Call<Moviwes2> call, Response<Moviwes2> responsee) {
                progressBar.setVisibility(View.INVISIBLE);
                if (page == 1) {

                    if (responsee.body() != null) {
                        generateData(responsee.body().getResults());
                    }

                } else {
                    List<Result> resultLists = null;
                    if (responsee.body() != null) {
                        resultLists= responsee.body().getResults();
                    }
                    for (Result result : resultLists) {
                        resultdatam.add(result);
                        listadapter.notifyItemInserted(resultdatam.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<Moviwes2> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lol  HAppened", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void generateData(List<Result> results) {

        listadapter = new Adapterr(results, new OnItemClickListener() {
            @Override
            public void onItemClick(Result result) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("myMovie", result);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(listadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meeenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favorites:
                movieViewModel.getFavoriteMovies().observe(this , it ->{
                    if (!it.isEmpty()){
                        setTitle("Your Favorites");
                        listadapter.setData(it);
                    }
                    else{
                        Toast.makeText(this, "No FAVorite Movies", Toast.LENGTH_SHORT).show();
                    }
                });

            case R.id.action_topRated:
                SORT_MODE = 2;
                setTitle("Top RATED");
                LoaddddMyPAgeee(PAGE);
                break;
            case R.id.action_popular:
                SORT_MODE = 1;
                setTitle("Populer");
                LoaddddMyPAgeee(PAGE);
                break;

        }


        return super.onOptionsItemSelected(item);
    }


}