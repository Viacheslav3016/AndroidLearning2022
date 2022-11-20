package com.example.mylovefilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieDetailViewModel movieDetailViewModel;
    private static final String EXTRA_MOVIE = "movie";
    private static final String TAG = "MovieDetailsActivity";

    private ImageView ImageViewPoster;
    private TextView TextViewTitle;
    private TextView TextViewYear;
    private TextView TextViewDescription;
    private RecyclerView rv_trailers;
    private TrailersAdapter trailersAdapter;
    private ReviewAdapter reviewAdapter;
    private RecyclerView rv_reviews;
    private ImageView imageViewStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        InitView();
        trailersAdapter = new TrailersAdapter();
        rv_trailers.setAdapter(trailersAdapter);
        reviewAdapter = new ReviewAdapter();
        rv_reviews.setAdapter(reviewAdapter);
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this).load(movie.getPoster().getUrl()).into(ImageViewPoster);
        TextViewTitle.setText(movie.getName());
        TextViewYear.setText(String.valueOf(movie.getYearFilm()));
        TextViewDescription.setText(movie.getDescriptionMov());

        trailersAdapter.setOnClickListenerTrailer(new TrailersAdapter.onClickListenerTrailer() {
            @Override
            public void onTrailerClick(Trailers trailers) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailers.getUrl()));
                startActivity(intent);
            }
        });

        movieDetailViewModel.loadTrailers(movie.getIdFilm());
        movieDetailViewModel.getTrailers().observe(this, new Observer<List<Trailers>>() {
            @Override
            public void onChanged(List<Trailers> trailers) {
                trailersAdapter.setTrailersL(trailers);
            }
        });
        movieDetailViewModel.loadReviews(movie.getIdFilm());

        movieDetailViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewAdapter.setReviewList(reviewList);
            }
        });
        Drawable starOff = ContextCompat.getDrawable(MovieDetailsActivity.this, android.R.drawable.star_off);
        Drawable starOn = ContextCompat.getDrawable(MovieDetailsActivity.this, android.R.drawable.star_on);

        movieDetailViewModel.getFavFilm(movie.getIdFilm()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDB) {
                if (movieFromDB == null) {
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.insertMovie(movie);
                        }
                    });
                } else {
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.deleteMovie(movie.getIdFilm());
                        }
                    });
                }
            }
        });
    }
    private void InitView() {
        ImageViewPoster = findViewById(R.id.ImageViewPoster);
        TextViewTitle = findViewById(R.id.TextViewTitle);
        TextViewYear = findViewById(R.id.TextViewYear);
        TextViewDescription = findViewById(R.id.TextViewDescription);
        rv_trailers = findViewById(R.id.rv_trailers);
        rv_reviews = findViewById(R.id.rv_reviews);
        imageViewStar = findViewById(R.id.ivAddFavourite);
    }
    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}