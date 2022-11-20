package com.example.mylovefilms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM FAVOURITE_FILM")
    LiveData<List<Movie>> getAllFavMovies();

    @Query("SELECT * FROM favourite_film WHERE idFilm = :movieid")
    LiveData<Movie> getFavMovie(int movieid);

    @Insert
    Completable insertMovie(Movie movie);

    @Query("DELETE FROM favourite_film WHERE idFilm = :movieid")
    Completable deleteMovie(int movieid);



}
