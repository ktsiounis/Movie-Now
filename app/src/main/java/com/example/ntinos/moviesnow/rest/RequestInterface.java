package com.example.ntinos.moviesnow.rest;

import com.example.ntinos.moviesnow.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Konstantinos Tsiounis on 21-Feb-18.
 */

public interface RequestInterface {
    @GET("movie/popular/")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String KEY);

    @GET("movie/top_rated/")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String KEY);
}
