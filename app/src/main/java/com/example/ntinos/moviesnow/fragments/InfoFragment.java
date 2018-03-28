package com.example.ntinos.moviesnow.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.activity.DetailsActivity;
import com.example.ntinos.moviesnow.data.FavoritesContract;
import com.example.ntinos.moviesnow.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InfoFragment extends Fragment {

    @BindView(R.id.rating) public RatingBar ratingBar;
    @BindView(R.id.description) public TextView description;
    @BindView(R.id.title) public TextView title;
    @BindView(R.id.poster) public ImageView poster;
    @BindView(R.id.floatingActionButton) public FloatingActionButton favBtn;
    @BindView(R.id.releaseDate) public TextView releaseDate;

    public static final String BASE_URL = "http://image.tmdb.org/t/p/w780";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);

        final Movie movie = (Movie) getArguments().getSerializable("movie");
        Log.d("ID", "onCreate: " + movie.getId());

        Picasso.with(getActivity())
                .load(BASE_URL + movie.getBackdrop())
                .error(R.drawable.ic_launcher_background)
                .into(poster);

        ratingBar.setRating(Float.valueOf(movie.getVoteAvg())/2);
        description.setText(movie.getDescription());
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = getActivity().getContentResolver().query(FavoritesContract.FavoritesEntry.CONTENT_URI,
                                                                    new String[]{FavoritesContract.FavoritesEntry.COLUMN_ID},
                                                                    "id=?",
                                                                    new String[]{String.valueOf(movie.getId())},
                                                                    null);

                if(cursor.getCount() != 0){
                    int favoritesDeleted = getActivity().getContentResolver().delete(FavoritesContract.FavoritesEntry.CONTENT_URI,
                                                                            "id=?",
                                                                                    new String[]{String.valueOf(movie.getId())});
                    if(favoritesDeleted > 0){
                        Toast.makeText(getActivity().getBaseContext(), "Favorite removed", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    ContentValues values = new ContentValues();

                    values.put(FavoritesContract.FavoritesEntry.COLUMN_ID, movie.getId());
                    values.put(FavoritesContract.FavoritesEntry.COLUMN_POSTER, movie.getThumbnail());
                    values.put(FavoritesContract.FavoritesEntry.COLUMN_RATING, movie.getVoteAvg());
                    values.put(FavoritesContract.FavoritesEntry.COLUMN_TITLE, movie.getTitle());

                    Uri uri = getActivity().getContentResolver().insert(FavoritesContract.FavoritesEntry.CONTENT_URI, values);

                    if(uri != null){
                        Toast.makeText(getActivity().getBaseContext(), "Marked as favorite", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
