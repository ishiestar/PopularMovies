package com.example.ishitasinha.moviesapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ishitasinha on 15/04/16.
 */
public class ActorPojo {
    @SerializedName("Name")
    String name;
    @SerializedName("ProfilePath")
    String picture;

    ActorPojo(String name, String picture) {
        this.name = name;
        this.picture = picture; 
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
