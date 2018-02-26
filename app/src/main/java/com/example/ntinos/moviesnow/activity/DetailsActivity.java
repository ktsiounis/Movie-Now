package com.example.ntinos.moviesnow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.rating)
    public RatingBar ratingBar;
    @BindView(R.id.description)
    public TextView description;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.poster)
    public ImageView poster;
    @BindView(R.id.favBtn)
    public ToggleButton favBtn;
    @BindView(R.id.releaseDate)
    public TextView releaseDate;
    public static final String BASE_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        Log.d("DATE", "onCreate: " + movie.getReleaseDate());

        Picasso.with(this)
                .load(BASE_URL + movie.getBackdrop())
                .error(R.drawable.ic_launcher_background)
                .into(poster);

        ratingBar.setRating(Float.valueOf(movie.getVoteAvg())/2);
        description.setText(movie.getDescription());
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
    }
}
