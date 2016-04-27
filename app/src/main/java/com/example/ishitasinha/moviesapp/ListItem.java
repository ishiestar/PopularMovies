package com.example.ishitasinha.moviesapp;

import com.google.gson.annotations.SerializedName;

/**
 * Movie POJO
 */
public class ListItem {
    @SerializedName("Description")
    String plot;
    @SerializedName("Title")
    String title;
    @SerializedName("PosterPath")
    String posterUrl;
    @SerializedName("Genre")
    String genre;
    @SerializedName("ReleaseDate")
    String releaseDate;
    @SerializedName("Rating")
    String rating;
    @SerializedName("CensorRating")
    String censor;

    ListItem(String plot, String title, String posterUrl, String genre, String rating, String releaseDate, String censor) {
        this.plot = plot;
        this.title = title;
        this.posterUrl = posterUrl;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.censor = censor;
    }

    public String getPlot() {
        return plot;
    }

    public String getCensor() {
        return censor;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
