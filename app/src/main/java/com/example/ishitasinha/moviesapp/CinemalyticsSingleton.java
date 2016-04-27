package com.example.ishitasinha.moviesapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ishitasinha on 15/04/16.
 */
public class CinemalyticsSingleton {
    static CinemalyticsApiService service;

    public static CinemalyticsApiService getService() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CinemalyticsApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(CinemalyticsApiService.class);
        }
        return service;
    }
}
