package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersLIst {

    @SerializedName("trailers")
    private List<Trailers> ListTrailers;

    public TrailersLIst(List<Trailers> listTrailers) {
        ListTrailers = listTrailers;
    }

    public List<Trailers> getListTrailers() {
        return ListTrailers;
    }

    @Override
    public String toString() {
        return "TrailersLIst{" +
                "ListTrailers=" + ListTrailers +
                '}';
    }
}
