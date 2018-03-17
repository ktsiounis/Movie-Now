package com.example.ntinos.moviesnow.model;

import android.widget.ListView;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dtsiounis on 17/03/2018.
 */

public class ReviewsResponse {

    @SerializedName("id") public String id;
    @SerializedName("page") public String page;
    @SerializedName("results") public List<Review> reviews;
    @SerializedName("total_results") public String total_results;

    public ReviewsResponse(String id, String page, List<Review> reviews, String total_results) {
        this.id = id;
        this.page = page;
        this.reviews = reviews;
        this.total_results = total_results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
}
