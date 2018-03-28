package com.example.ntinos.moviesnow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ntinos.moviesnow.data.FavoritesContract.FavoritesEntry;

/**
 * Created by dtsiounis on 25/03/2018.
 */

public class FavoritesDBHelper extends SQLiteOpenHelper {

    // The name of the database
    private static final String DATABASE_NAME = "favoritesDB.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;

    public FavoritesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " + FavoritesEntry.TABLE_NAME + " (" +
                FavoritesEntry._ID + " INTEGER PRIMARY KEY, "     +
                FavoritesEntry.COLUMN_ID + " TEXT NOT NULL, "     +
                FavoritesEntry.COLUMN_TITLE + " TEXT NOT NULL, "  +
                FavoritesEntry.COLUMN_POSTER + " TEXT NOT NULL, " +
                FavoritesEntry.COLUMN_RATING + " TEXT NOT NULL);" ;
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesEntry.TABLE_NAME);
        onCreate(db);
    }
}
