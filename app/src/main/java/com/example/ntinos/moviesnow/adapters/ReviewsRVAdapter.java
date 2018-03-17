package com.example.ntinos.moviesnow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dtsiounis on 17/03/2018.
 */

public class ReviewsRVAdapter extends RecyclerView.Adapter<ReviewsRVAdapter.ReviewViewHolder>{

    private List<Review> reviews;

    public ReviewsRVAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card, parent, false);;
        return new ReviewViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.author.setText(reviews.get(position).author);
        holder.content.setText(reviews.get(position).content);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.author) public TextView author;
        @BindView(R.id.content) public TextView content;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
