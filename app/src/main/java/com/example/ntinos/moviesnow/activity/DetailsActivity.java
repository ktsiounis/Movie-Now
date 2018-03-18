package com.example.ntinos.moviesnow.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.adapters.DetailsPagerAdapter;
import com.example.ntinos.moviesnow.adapters.ReviewsRVAdapter;
import com.example.ntinos.moviesnow.fragments.InfoFragment;
import com.example.ntinos.moviesnow.fragments.ReviewsFragment;
import com.example.ntinos.moviesnow.fragments.TrailersFragment;
import com.example.ntinos.moviesnow.model.Movie;
import com.example.ntinos.moviesnow.model.MoviesResponse;
import com.example.ntinos.moviesnow.model.Review;
import com.example.ntinos.moviesnow.model.ReviewsResponse;
import com.example.ntinos.moviesnow.model.Trailer;
import com.example.ntinos.moviesnow.model.TrailersResponse;
import com.example.ntinos.moviesnow.rest.APIClient;
import com.example.ntinos.moviesnow.rest.RequestInterface;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.viewpager) public ViewPager mViewPager;
    @BindView(R.id.tabs) public TabLayout mTabLayout;

    public DetailsPagerAdapter mDetailsPagerAdapter;
    public List<Review> reviews;
    public List<Trailer> trailers;
    public Bundle data;
    public String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        API_KEY = getResources().getString(R.string.API_KEY);
        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        setTitle(movie.getTitle());

        data = new Bundle();
        data.putSerializable("movie", movie);

        mDetailsPagerAdapter = new DetailsPagerAdapter(getSupportFragmentManager());
        mDetailsPagerAdapter.addFragment(new InfoFragment(), "Info", data);
        mDetailsPagerAdapter.addFragment(new TrailersFragment(), "Trailers", data);
        mDetailsPagerAdapter.addFragment(new ReviewsFragment(), "Reviews", data);
        mViewPager.setAdapter(mDetailsPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }
}
