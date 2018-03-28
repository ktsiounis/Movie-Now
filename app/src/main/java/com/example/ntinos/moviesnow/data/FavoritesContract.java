package com.example.ntinos.moviesnow.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dtsiounis on 25/03/2018.
 */

public class FavoritesContract {

    // The authority, which is how my code know which Content Provider to access
    public static final String AUTHORITY = "com.example.ntinos.moviesnow";

    // The base URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // This is the path for the "favorites" directory
    public static final String PATH_FAVORITES = "favorites";

    public static final class FavoritesEntry {

        // FavoritesEntry content URI = base content URI + path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

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
