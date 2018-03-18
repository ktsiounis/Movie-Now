package com.example.ntinos.moviesnow.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dtsiounis on 17/03/2018.
 */

public class Trailer implements Serializable{

    @SerializedName("id") public String id;
    @SerializedName("key") public String key;
    @SerializedName("name") public String name;
    @SerializedName("iso_639_1") public String iso_639_1;
    @SerializedName("iso_3166_1") public String iso_3166_1;
    @SerializedName("site") public String site;
    @SerializedName("size") public String size;
    @SerializedName("type") public String type;

    public Trailer(String id, String key, String name, String iso_639_1, String iso_3166_1, String site, String size, String type) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}