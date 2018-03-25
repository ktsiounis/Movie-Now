package com.example.ntinos.moviesnow.data;

import android.provider.BaseColumns;

/**
 * Created by dtsiounis on 25/03/2018.
 */

public class FavoritesContract {

    public static final class FavoritesEntry implements BaseColumns{

        // Favorites table and column names
        public static final String TABLE_NAME = "favorites";

        // Since FavoritesEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_RATING = "rating";

    }

}
