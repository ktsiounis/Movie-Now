package com.example.ntinos.moviesnow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Konstantinos Tsiounis on 21-Feb-18.
 */

public class MoviesResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
