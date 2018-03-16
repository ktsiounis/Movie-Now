package com.example.ntinos.moviesnow.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.activity.DetailsActivity;
import com.example.ntinos.moviesnow.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InfoFragment extends Fragment {

    @BindView(R.id.rating) public RatingBar ratingBar;
    @BindView(R.id.description) public TextView description;
    @BindView(R.id.title) public TextView title;
    @BindView(R.id.poster) public ImageView poster;
    @BindView(R.id.favBtn) public ToggleButton favBtn;
    @BindView(R.id.releaseDate) public TextView releaseDate;

    public static final String BASE_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);

        Movie movie = (Movie) getArguments().getSerializable("movie");
        Log.d("DATE", "onCreate: " + movie.getReleaseDate());

        Picasso.with(getActivity())
                .load(BASE_URL + movie.getBackdrop())
                .error(R.drawable.ic_launcher_background)
                .into(poster);

        ratingBar.setRating(Float.valueOf(movie.getVoteAvg())/2);
        description.setText(movie.getDescription());
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());

        // Inflate the layout for this fragment
        return view;
    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();

        return fragment;
    }
}
