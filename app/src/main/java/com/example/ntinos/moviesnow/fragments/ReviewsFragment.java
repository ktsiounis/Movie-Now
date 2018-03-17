package com.example.ntinos.moviesnow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.activity.DetailsActivity;
import com.example.ntinos.moviesnow.adapters.ReviewsRVAdapter;
import com.example.ntinos.moviesnow.model.Movie;
import com.example.ntinos.moviesnow.model.Review;
import com.example.ntinos.moviesnow.model.ReviewsResponse;
import com.example.ntinos.moviesnow.rest.APIClient;
import com.example.ntinos.moviesnow.rest.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewsFragment extends Fragment {

    @BindView(R.id.reviewsRV) public RecyclerView reviewsRV;
    public List<Review> reviews;
    private ReviewsRVAdapter mReviewsRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        reviewsRV.setLayoutManager(mLayoutManager);
        reviewsRV.setItemAnimator(new DefaultItemAnimator());
        reviewsRV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        reviews = (List<Review>) getArguments().getSerializable("reviews");

        mReviewsRVAdapter = new ReviewsRVAdapter(reviews);
        reviewsRV.setAdapter(mReviewsRVAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
