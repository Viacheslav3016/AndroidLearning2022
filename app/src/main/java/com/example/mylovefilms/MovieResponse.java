package com.example.mylovefilms;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("docs")
    private List<Movie> Movies;

    public MovieResponse(List<Movie> movies) {
        Movies = movies;
    }

    public List<Movie> getMovies() {
        return Movies;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieResponse{" +
                "Movies=" + Movies +
                '}';
    }
}
