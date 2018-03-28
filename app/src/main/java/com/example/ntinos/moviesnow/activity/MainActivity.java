package com.example.ntinos.moviesnow.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ntinos.moviesnow.R;
import com.example.ntinos.moviesnow.adapters.MoviesRVAdapter;
import com.example.ntinos.moviesnow.data.FavoritesContract;
import com.example.ntinos.moviesnow.model.MoviesResponse;
import com.example.ntinos.moviesnow.model.Movie;
import com.example.ntinos.moviesnow.rest.APIClient;
import com.example.ntinos.moviesnow.rest.RequestInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesRVAdapter.ItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.content_movies)
    public RecyclerView content_moviesRV;
    private MoviesRVAdapter moviesAdapter;
    private List<Movie> movieList;
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

        getSupportLoaderManager().initLoader(FAVORITES_LOADER_ID,null, this);

        fetchPopularMovies();
    }

    public void fetchPopularMovies(){

        RequestInterface requestInterface = APIClient.getClient().create(RequestInterface.class);
        Call<MoviesResponse> call = requestInterface.getPopularMovies(API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movieList = response.body().getMovies();
                moviesAdapter = new MoviesRVAdapter(movieList, MainActivity.this, MainActivity.this);
                content_moviesRV.setAdapter(moviesAdapter);
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
                moviesAdapter = new MoviesRVAdapter(movieList, MainActivity.this, MainActivity.this);
                content_moviesRV.setAdapter(moviesAdapter);
                Log.d("RESPONSE", "Number of movies received: " + movieList.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("ERROR ON RESPONSE", t.toString());
            }
        });
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

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
