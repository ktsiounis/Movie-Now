package com.example.ntinos.moviesnow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Konstantinos Tsiounis on 20/02/2018.
 */

public class Movie implements Parcelable {

    @SerializedName("original_title")
    private String title;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String thumbnail;
    @SerializedName("vote_average")
    private String voteAvg;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("id")
    private int id;

    public Movie(String title, String description, String thumbnail, String voteAvg, String releaseDate, Boolean adult, String backdrop, int id) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.voteAvg = voteAvg;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.backdrop = backdrop;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(String voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(thumbnail);
        parcel.writeString(voteAvg);
        parcel.writeString(releaseDate);
        parcel.writeString(backdrop);
        parcel.writeInt(id);
    }

    // Method to recreate a Question from a Parcel
    public static Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }

    };

    public Movie (Parcel parcel) {
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbnail = parcel.readString();
        this.voteAvg = parcel.readString();
        this.releaseDate = parcel.readString();
        this.backdrop = parcel.readString();
        this.id = parcel.readInt();
    }
}
