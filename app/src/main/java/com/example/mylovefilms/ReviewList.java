package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewList {

    @SerializedName("review")
    private List<Review> reviewListi;

    public ReviewList(List<Review> reviewList) {
        reviewListi = reviewList;
    }

    public List<Review> getReviewList() {
        return reviewListi;
    }

    @Override
    public String toString() {
        return "ReviewList{" +
                "reviewList=" + reviewListi +
                '}';
    }
}
