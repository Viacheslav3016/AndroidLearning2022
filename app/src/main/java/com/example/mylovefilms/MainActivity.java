package com.example.mylovefilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
   private RecyclerView rv_movies;
   private MoviesAdapter moviesAdapter;
   private ProgressBar progressBarL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        InitElementsById();
        moviesAdapter = new MoviesAdapter();
        rv_movies.setAdapter(moviesAdapter);
        rv_movies.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
               moviesAdapter.setMovies(movies);
            }
        });

        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading){
                    progressBarL.setVisibility(View.VISIBLE);
                }else{
                    progressBarL.setVisibility(View.GONE);
                }
            }
        });

        moviesAdapter.setOnClickMovieListener(new MoviesAdapter.OnClickMovieListener() {
            @Override
            public void onClickMovie(Movie movie) {
                Intent intent = MovieDetailsActivity.newIntent(MainActivity.this, movie);
                startActivity(intent);
            }
        });

        moviesAdapter.setOnReachEndListener(new MoviesAdapter.onReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadMovies();
            }
        });
    }
    public void InitElementsById(){
        rv_movies = findViewById(R.id.rv_movies);
        progressBarL = findViewById(R.id.progressBarL);
    }
}