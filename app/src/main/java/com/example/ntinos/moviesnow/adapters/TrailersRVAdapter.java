package com.example.ntinos.moviesnow.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dtsiounis on 18/03/2018.
 */

public class TrailersRVAdapter extends RecyclerView.Adapter<TrailersRVAdapter.TrailersViewHolder> {

    private List<Trailer> trailers;

    public TrailersRVAdapter(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @Override
    public TrailersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_card, parent, false);
        return new TrailersViewHolder(card);
    }

    @Override
    public void onBindViewHolder(TrailersViewHolder holder, int position) {
        holder.trailer_name.setText(trailers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_name) public TextView trailer_name;

        public TrailersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
