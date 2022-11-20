package com.example.mylovefilms;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersRespons {

    @SerializedName("videos")
    private TrailersLIst trailersLIst;

    public TrailersRespons(TrailersLIst trailersLIst) {
        this.trailersLIst = trailersLIst;
    }

    public TrailersLIst getTrailersLIst() {
        return trailersLIst;
    }

    @Override
    public String toString() {
        return "TrailersRespons{" +
                "trailersLIst=" + trailersLIst +
                '}';
    }
}

