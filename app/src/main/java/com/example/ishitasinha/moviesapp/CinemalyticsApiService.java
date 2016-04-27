package com.example.ishitasinha.moviesapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CinemalyticsApiService {

    static final String BASE_URL = "https://api.cinemalytics.com/";
    static final String AUTH_TOKEN = "978FAF701252360FA0E0BC15D59EDAC1";

    @GET("v1/movie/releasedthisweek")
    Call<List<ListItem>> thisWeek(@Query("auth_token") String authToken);

    @GET("v1/movie/nextchange")
    Call<List<ListItem>> nextChange(@Query("auth_token") String authToken);

    @GET("v1/analytics/TopMovies")
    Call<List<ListItem>> topTen(@Query("auth_token") String authToken);

    @GET("v1/actor/name/{searchTerm}/")
    Call<List<ActorPojo>> actors(@Path("searchTerm") String searchTerm, @Query("auth_token") String authToken);

    @GET("v1/movie/title/")
    Call<List<ListItem>> movieSearchTitle(@Query("value") String value, @Query("auth_token") String authToken);

    @GET("v1/movie/year/{released}/")
    Call<List<ListItem>> movieSearchReleased(@Path("released") String year, @Query("auth_token") String authToken);
}