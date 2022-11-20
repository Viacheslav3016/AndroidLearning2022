package com.example.mylovefilms;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//    @GET("movie?token=G25Y0PE-ADH458D-KD01W06-4FJ65ME&field=rating.kp&search=4-10&field=year&search=2015-2022&sortField=votes.kp&sortType=-1&limit=5")
    @GET("movie?token=G25Y0PE-ADH458D-KD01W06-4FJ65ME&field=rating.kp&search=4-10&field=year&search=2012-2022&sortField=votes.kp&sortType=-1&limit=50")
    Single<MovieResponse> loadMovie(@Query("page") int page);

    @GET("movie?token=G25Y0PE-ADH458D-KD01W06-4FJ65ME&field=id")
    Single<TrailersRespons> loadTrailers(@Query("search") int id);

    @GET("review?token=G25Y0PE-ADH458D-KD01W06-4FJ65ME&field=movieid")
    Single<ReviewRespons> loadReview(@Query("search")int id);
}
