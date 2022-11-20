package com.example.mylovefilms;

import com.google.gson.annotations.SerializedName;

public class Trailers {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public Trailers(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Trailers{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
