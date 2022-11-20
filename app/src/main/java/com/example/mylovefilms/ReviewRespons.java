package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

public class ReviewRespons {

    @SerializedName("docs")
    private ReviewList reviewList;

    public ReviewRespons(ReviewList reviewList) {
        this.reviewList = reviewList;
    }

    public ReviewList getReviewList() {
        return reviewList;
    }

    @Override
    public String toString() {
        return "ReviewRespons{" +
                "reviewList=" + reviewList +
                '}';
    }
}
