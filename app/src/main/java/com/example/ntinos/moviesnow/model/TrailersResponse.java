package com.example.ntinos.moviesnow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dtsiounis on 17/03/2018.
 */

public class TrailersResponse {

    @SerializedName("id") public String id;
    @SerializedName("results") public List<Trailer> trailers;

    public TrailersResponse(String id, List<Trailer> trailers) {
        this.id = id;
        this.trailers = trailers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
