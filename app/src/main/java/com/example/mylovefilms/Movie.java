package com.example.mylovefilms;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favourite_film")
public class Movie implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private final int idFilm;

    @SerializedName("name")
    private final String name;

    @SerializedName("description")
    private final String descriptionMov;

    @SerializedName("year")
    private final int yearFilm;

    @SerializedName("poster")
    @Embedded
    private final Poster poster;

    @SerializedName("rating")
    @Embedded
    private final Rating rating;

    public Movie(int idFilm, String name, String descriptionMov, int yearFilm, Poster poster, Rating rating) {
        this.idFilm = idFilm;
        this.name = name;
        this.descriptionMov = descriptionMov;
        this.yearFilm = yearFilm;
        this.poster = poster;
        this.rating = rating;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public String getName() {
        return name;
    }

    public String getDescriptionMov() {
        return descriptionMov;
    }

    public int getYearFilm() {
        return yearFilm;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Movie{" +
                "idFilm=" + idFilm +
                ", name='" + name + '\'' +
                ", descriptionMov='" + descriptionMov + '\'' +
                ", yearFilm=" + yearFilm +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
