package com.example.ishitasinha.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView thisWeek, nextChange, topTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        thisWeek = (RecyclerView) findViewById(R.id.rv_this_week);
        nextChange = (RecyclerView) findViewById(R.id.rv_next_change);
        topTen = (RecyclerView) findViewById(R.id.rv_top_ten);

        thisWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nextChange.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topTen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        CinemalyticsApiService service = CinemalyticsSingleton.getService();

        service.thisWeek(CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ListItem>>() {
            @Override
            public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                Log.v("this week", response.raw().toString());
                MoviesAdapter adapter = new MoviesAdapter(response.body());
                thisWeek.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListItem>> call, Throwable t) {

            }
        });

        service.nextChange(CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ListItem>>() {
            @Override
            public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                Log.v("next change", response.raw().toString());
                MoviesAdapter adapter = new MoviesAdapter(response.body());
                nextChange.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListItem>> call, Throwable t) {

            }
        });

        service.topTen(CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ListItem>>() {
            @Override
            public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                Log.v("top ten", response.raw().toString());
                MoviesAdapter adapter = new MoviesAdapter(response.body());
                topTen.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListItem>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
