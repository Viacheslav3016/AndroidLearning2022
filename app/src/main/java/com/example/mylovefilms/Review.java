package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("type")
    private String typeOfReview;
    @SerializedName("review")
    private String textOfreview;
    @SerializedName("author")
    private String authorOfReviw;

    public Review(String typeOfReview, String textOfreview, String authorOfReviw) {
        this.typeOfReview = typeOfReview;
        this.textOfreview = textOfreview;
        this.authorOfReviw = authorOfReviw;
    }

    public String getTypeOfReview()
    {
        return typeOfReview;
    }

    public String getTextOfreview()
    {
        return textOfreview;
    }

    public String getAuthorOfReviw() {
        return authorOfReviw;
    }

    @Override
    public String toString() {
        return "Review{" +
                ", typeOfReview='" + typeOfReview + '\'' +
                ", textOfreview='" + textOfreview + '\'' +
                ", authorOfReviw='" + authorOfReviw + '\'' +
                '}';
    }
}
