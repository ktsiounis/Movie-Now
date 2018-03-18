package com.example.ntinos.moviesnow.fragments;

import android.content.Intent;
import android.net.Uri;
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

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.activity.DetailsActivity;
import com.example.ntinos.moviesnow.adapters.TrailersRVAdapter;
import com.example.ntinos.moviesnow.model.Movie;
import com.example.ntinos.moviesnow.model.Trailer;
import com.example.ntinos.moviesnow.model.TrailersResponse;
import com.example.ntinos.moviesnow.rest.APIClient;
import com.example.ntinos.moviesnow.rest.RequestInterface;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailersFragment extends Fragment implements TrailersRVAdapter.ItemClickListener {

    @BindView(R.id.trailersRV) public RecyclerView trailersRV;
    public List<Trailer> trailers;
    public Movie movie;
    public TrailersRVAdapter mTrailersRVAdapter;
    public String API_KEY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        API_KEY = getResources().getString(R.string.API_KEY);

        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        trailersRV.setLayoutManager(mLayoutManager);
        trailersRV.setItemAnimator(new DefaultItemAnimator());
        trailersRV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        movie = (Movie) getArguments().getSerializable("movie");
        fetchTrailers(movie);

        // Inflate the layout for this fragment
        return view;
    }

    public void fetchTrailers(Movie movie){
        RequestInterface requestInterface = APIClient.getClient().create(RequestInterface.class);
        Call<TrailersResponse> call = requestInterface.getTrailers(movie.getId(), API_KEY);

        call.enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                trailers = response.body().getTrailers();
                mTrailersRVAdapter = new TrailersRVAdapter(trailers, TrailersFragment.this);
                trailersRV.setAdapter(mTrailersRVAdapter);
            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {
                Log.e("ERROR ON RESPONSE", t.toString());
            }
        });
    }

    @Override
    public void onItemClickListener(int position) {
        Uri webpage = Uri.parse(trailers.get(position).getKey());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        //intent.putExtra("movie", movieList.get(position));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            startActivity(Intent.createChooser(intent,"Chooser"));
        }
    }
}
