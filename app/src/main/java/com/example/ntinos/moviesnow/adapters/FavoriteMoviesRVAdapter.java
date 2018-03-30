package com.example.ntinos.moviesnow.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.data.FavoritesContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Konstantinos Tsiounis on 28-Mar-18.
 */
public class FavoriteMoviesRVAdapter extends RecyclerView.Adapter<FavoriteMoviesRVAdapter.FavoriteMoviesViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    private ItemClickListener mListener;
    private static final String BASE_URL = "http://image.tmdb.org/t/p/w500";

    public FavoriteMoviesRVAdapter(Cursor mCursor, Context mContext, ItemClickListener mListener) {
        this.mCursor = mCursor;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public FavoriteMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new FavoriteMoviesViewHolder(card);
    }

    @Override
    public void onBindViewHolder(FavoriteMoviesViewHolder holder, int position) {

        int idIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_ID);
        int titleIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_TITLE);
        int thumbnailIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_POSTER);
        int ratingIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_RATING);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String title = mCursor.getString(titleIndex);
        String thumbnail = mCursor.getString(thumbnailIndex);
        String rating = mCursor.getString(ratingIndex);

        holder.itemView.setTag(id);
        holder.title.setText(title);
        holder.rating.setRating(Float.parseFloat(rating)/2);
        Picasso.with(mContext)
                .load(BASE_URL + thumbnail)
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public class FavoriteMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_title) public TextView title;
        @BindView(R.id.ratingBar) public RatingBar rating;
        @BindView(R.id.movie_thumbnail) public ImageView thumbnail;

        public FavoriteMoviesViewHolder(View itemView) {
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

    public interface ItemClickListener{
        void onItemClickListener(int position);
    }
}
