package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable{

    @SerializedName("kp")
    private  double kp;

    public Rating(double kp) {
        this.kp = kp;
    }

    public double getKp() {
        return kp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "raitingKP=" + kp +
                '}';
    }
}
