package com.example.ntinos.moviesnow.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.adapters.FavoriteMoviesRVAdapter;
import com.example.ntinos.moviesnow.adapters.MoviesRVAdapter;
import com.example.ntinos.moviesnow.data.FavoritesContract;
import com.example.ntinos.moviesnow.model.MoviesResponse;
import com.example.ntinos.moviesnow.model.Movie;
import com.example.ntinos.moviesnow.rest.APIClient;
import com.example.ntinos.moviesnow.rest.RequestInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesRVAdapter.ItemClickListener, LoaderManager.LoaderCallbacks<Cursor>, FavoriteMoviesRVAdapter.ItemClickListener {

    @BindView(R.id.content_movies) public RecyclerView content_moviesRV;
    @BindView(R.id.activity_main_swipe_refresh_layout) public SwipeRefreshLayout refreshLayout;
    private MoviesRVAdapter moviesAdapter;
    private FavoriteMoviesRVAdapter favoriteMoviesRVAdapter;
    private ArrayList<Movie> movieList;
    private String API_KEY;
    private static final int FAVORITES_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        API_KEY = getResources().getString(R.string.API_KEY);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        content_moviesRV.setLayoutManager(mLayoutManager);
        content_moviesRV.setItemAnimator(new DefaultItemAnimator());
        content_moviesRV.setHasFixedSize(true);
        moviesAdapter = new MoviesRVAdapter(this, this);
        content_moviesRV.setAdapter(moviesAdapter);

        getSupportLoaderManager().initLoader(FAVORITES_LOADER_ID,null, this);

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                                              android.R.color.holo_red_light,
                                              android.R.color.holo_green_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isOnline()){
                    fetchPopularMovies();
                    refreshLayout.setRefreshing(false);
                }
                else{
                    Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
            }
        });

        if(savedInstanceState != null && savedInstanceState.containsKey("MOVIES_DATA")){
            movieList = savedInstanceState.getParcelableArrayList("MOVIES_DATA");
            Log.d("onSaveInstanceState", "onCreate: data retrieved from saveInstanceState " + movieList.get(1).getTitle());
            moviesAdapter.swapAdapters(movieList);
            //moviesAdapter = new MoviesRVAdapter(movieList, mainContext, MainActivity.this);
            //content_moviesRV.setAdapter(moviesAdapter);
        }
        else {
            if(isOnline()){
                fetchPopularMovies();
            }
            else{
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void fetchPopularMovies(){

        RequestInterface requestInterface = APIClient.getClient().create(RequestInterface.class);
        Call<MoviesResponse> call = requestInterface.getPopularMovies(API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieList = response.body().getMovies();
                moviesAdapter.swapAdapters(movieList);
                //moviesAdapter = new MoviesRVAdapter(movieList, mainContext, MainActivity.this);
                //content_moviesRV.setAdapter(moviesAdapter);
                Log.d("RESPONSE", "Number of movies received: " + movieList.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("ERROR ON RESPONSE", t.toString());
            }
        });
    }

    public void fetchTopRatedMovies(){
        RequestInterface requestInterface = APIClient.getClient().create(RequestInterface.class);
        Call<MoviesResponse> call = requestInterface.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieList = response.body().getMovies();
                moviesAdapter.swapAdapters(movieList);
                //moviesAdapter = new MoviesRVAdapter(movieList, MainActivity.this, MainActivity.this);
                //content_moviesRV.setAdapter(moviesAdapter);
                Log.d("RESPONSE", "Number of movies received: " + movieList.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("ERROR ON RESPONSE", t.toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the fetched movies
        outState.putParcelableArrayList("MOVIES_DATA", movieList);
        Log.d("onSaveInstanceState", "onSaveInstanceState: data saved");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orderByPopular:
                fetchPopularMovies();
                return true;
            case R.id.orderByTopRated:
                fetchTopRatedMovies();
                return true;
            case R.id.favorites:
                getSupportLoaderManager().restartLoader(FAVORITES_LOADER_ID, null,this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movieList.get(position));
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            Cursor mFavoriteData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mFavoriteData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mFavoriteData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(FavoritesContract.FavoritesEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e("AsyncTaskLoader", "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mFavoriteData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //favoriteMoviesRVAdapter = new FavoriteMoviesRVAdapter(data, MainActivity.this, MainActivity.this);
        //content_moviesRV.setAdapter(favoriteMoviesRVAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
