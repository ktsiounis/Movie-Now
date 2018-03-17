package com.example.ntinos.moviesnow.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dtsiounis on 17/03/2018.
 */

public class Review implements Serializable {

    @SerializedName("author") public String author;
    @SerializedName("content") public String content;
    @SerializedName("id") public String id;
    @SerializedName("url") public String url;

    public Review(String author, String content, String id, String url) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
