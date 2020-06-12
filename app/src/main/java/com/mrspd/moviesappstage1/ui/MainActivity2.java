package com.mrspd.moviesappstage1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrspd.moviesappstage1.MyExcecutors;
import com.mrspd.moviesappstage1.R;
import com.mrspd.moviesappstage1.adapters.MovieReviewsAdapter;
import com.mrspd.moviesappstage1.adapters.MovieTrailerAdapter;
import com.mrspd.moviesappstage1.api.Api;
import com.mrspd.moviesappstage1.api.RetrofitInstance;
import com.mrspd.moviesappstage1.model.Result;
import com.mrspd.moviesappstage1.model.Reviews;
import com.mrspd.moviesappstage1.model.Trailer;
import com.mrspd.moviesappstage1.model.Trailers;
import com.mrspd.moviesappstage1.viewmodel.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Created by Satyamurti Doddini with ❤  only for udacity
 */
public class MainActivity2 extends AppCompatActivity {
    private MovieViewModel movieViewModel;

    ImageView image, favoriteBtn;
    TextView date, rating, title, plot, votes, overview;
    private RecyclerView recyclerView;
    private RecyclerView reviewsss;
    private MovieTrailerAdapter movieTrailerAdapter;
    private MovieReviewsAdapter movieReviewAdapter;
    private List<Trailer> trailers;
    private Call<Trailers> trailerCallll;
    private Call<Reviews> reviewCAlllll;
    private Api api;
    Result movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        image = findViewById(R.id.ig);
        favoriteBtn = findViewById(R.id.igfav);
        date = findViewById(R.id.movie_date);
        rating = findViewById(R.id.ratingsss);
        title = findViewById(R.id.titleee);
        overview = findViewById(R.id.movie_overview);
        Bundle bundle = getIntent().getExtras();
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movie = (Result) bundle.getSerializable("myMovie");
        Glide.with(this)
                .load(imagePath(movie.getPosterPath()))
                .into(image);
        date.setText(movie.getReleaseDate());
        rating.setText(movie.getVoteAverage().toString());
        title.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());

        recyclerView = findViewById(R.id.trailerrrr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        reviewsss = findViewById(R.id.reviewwww);
        reviewsss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        TrailerLoad(movie.getId());
        TrailerReview(movie.getId());
    }

    public void Go(View view) {
        MyExcecutors.getInstance().diskIO().execute(() -> {
            boolean isFavorite = movieViewModel.isFavorite(movie.getId());
            if (isFavorite) {
                movieViewModel.deleteThisMovie(movie);
                runOnUiThread(() -> {
                    favoriteBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24));
                    Toast.makeText(this, "Favorite Movie Removed", Toast.LENGTH_SHORT).show();
                });
            } else {
                movieViewModel.addrthisMovie(movie);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Your Movie Added to Favorite :)))", Toast.LENGTH_SHORT).show();
                    favoriteBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24));
                });
            }
        });
    }


    private void TrailerLoad(int movieId) {

        if (api == null) {
            api = RetrofitInstance.getMyMovies(this);

        }
        trailerCallll = api.getMyTrailer(movieId, this.getString(R.string.apiKey));
        trailerCallll.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Response", "Successful");
                    movieTrailerAdapter = new MovieTrailerAdapter(response.body().getTrailers(), getApplicationContext());
                    if (response.body().getTrailers() != null) {
                        recyclerView.setAdapter(movieTrailerAdapter);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void TrailerReview(int movieId) {

        if (api == null) {
            api = RetrofitInstance.getMyMovies(this);
        }
        reviewCAlllll = api.getMyReviews(movieId, this.getString(R.string.apiKey), 1);
        reviewCAlllll.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieReviewAdapter = new MovieReviewsAdapter(response.body().getReviews(), getApplicationContext());
                    if (response.body().getReviews() != null) {
                        reviewsss.setAdapter(movieReviewAdapter);
                    } else {
                        reviewsss.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });

    }

    public static String imagePath(String path) {
        return "https://image.tmdb.org/t/p/" +
                "w500" +
                path;
    }
}