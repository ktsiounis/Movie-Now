package com.example.ntinos.moviesnow.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.adapter.DetailsPagerAdapter;
import com.example.ntinos.moviesnow.fragments.InfoFragment;
import com.example.ntinos.moviesnow.fragments.ReviewsFragment;
import com.example.ntinos.moviesnow.fragments.TrailersFragment;
import com.example.ntinos.moviesnow.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.viewpager) public ViewPager mViewPager;
    @BindView(R.id.tabs) public TabLayout mTabLayout;

    public DetailsPagerAdapter mDetailsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        Bundle data = new Bundle();
        data.putSerializable("movie", movie);

        setTitle(movie.getTitle());

        mDetailsPagerAdapter = new DetailsPagerAdapter(getSupportFragmentManager());
        mDetailsPagerAdapter.addFragment(new InfoFragment(), "Info", data);
        mDetailsPagerAdapter.addFragment(new TrailersFragment(), "Trailers", null);
        mDetailsPagerAdapter.addFragment(new ReviewsFragment(), "Reviews", null);
        mViewPager.setAdapter(mDetailsPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }
}
