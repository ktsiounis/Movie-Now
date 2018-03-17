package com.example.ntinos.moviesnow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Konstantinos Tsiounis on 20-Feb-18.
 */

public class MoviesCardViewAdapter extends RecyclerView.Adapter<MoviesCardViewAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private Context mContext;
    public static final String BASE_URL = "http://image.tmdb.org/t/p/w500";
    private ItemClickListener mListener;

    public MoviesCardViewAdapter(List<Movie> mMovies, Context mContext, ItemClickListener mListener) {
        this.mMovies = mMovies;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.movie_title) public TextView title;
        @BindView(R.id.ratingBar) public RatingBar rating;
        @BindView(R.id.movie_thumbnail) public ImageView thumbnail;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onItemClickListener(position);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(card);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.title.setText(mMovies.get(position).getTitle());
        holder.rating.setRating(Float.parseFloat(mMovies.get(position).getVoteAvg())/2);
        Picasso.with(mContext)
                .load(BASE_URL + mMovies.get(position).getThumbnail())
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface ItemClickListener{
        void onItemClickListener(int position);
    }
}
