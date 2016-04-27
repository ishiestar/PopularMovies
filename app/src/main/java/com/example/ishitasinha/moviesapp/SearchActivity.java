package com.example.ishitasinha.moviesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    final int ACTORS = 1;
    final int MOVIE_BY_NAME = 2;
    final int MOVIE_BY_YEAR = 3;

    int searchType;

    AutoCompleteTextView searchBar;
    Button searchButton;
    RecyclerView searchResults;
    RadioGroup radioGroup;
    Set<String> history;
    ArrayAdapter<String> adapter;
    ArrayList<String> searchList;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        radioGroup = (RadioGroup) findViewById(R.id.search_params);
        searchBar = (AutoCompleteTextView) findViewById(R.id.search_text);
        history = preferences.getStringSet("history", new HashSet<String>());
        Log.v("history", history.toString());
        searchList = new ArrayList<>(history);
//        if(!history.isEmpty()) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchList);
            searchBar.setAdapter(adapter);
//        }
        searchButton = (Button) findViewById((R.id.search_button));
        searchResults = (RecyclerView) findViewById(R.id.rv_search_results);
        searchResults.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                searchBar.setVisibility(View.VISIBLE);
                searchButton.setVisibility(View.VISIBLE);
                switch (checkId) {
                    case R.id.search_movie_year:
                        searchBar.setInputType(InputType.TYPE_CLASS_NUMBER);
                        searchType = MOVIE_BY_YEAR;
                        break;
                    case R.id.search_actor:
                        searchBar.setInputType(InputType.TYPE_CLASS_TEXT);
                        searchType = ACTORS;
                        break;
                    case R.id.search_movie_name:
                        searchBar.setInputType(InputType.TYPE_CLASS_TEXT);
                        searchType = MOVIE_BY_NAME;
                        break;
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CinemalyticsApiService service = CinemalyticsSingleton.getService();
                String searchTerm = searchBar.getText().toString();
                if (searchTerm.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter something to search for.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!history.contains(searchTerm))
                    {
                        history.add(searchTerm);
//                        editor.clear();
                        editor.putStringSet("history", history);
                        Log.v("history", history.toString());
                        editor.apply();
                        searchList.add(searchTerm);
                        adapter.notifyDataSetChanged();
                    }
                    switch (searchType) {
                        case ACTORS:
                            service.actors(searchTerm, CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ActorPojo>>() {
                                @Override
                                public void onResponse(Call<List<ActorPojo>> call, Response<List<ActorPojo>> response) {
                                    ActorsAdapter adapter = new ActorsAdapter(response.body());
                                    searchResults.setAdapter(adapter);
                                    if (response.body().isEmpty())
                                        Toast.makeText(getApplicationContext(), "No results. Please consider changing your search terms.", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<List<ActorPojo>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case MOVIE_BY_NAME:
                            service.movieSearchTitle(searchTerm, CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ListItem>>() {
                                @Override
                                public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                                    MoviesAdapter adapter = new MoviesAdapter(response.body());
                                    searchResults.setAdapter(adapter);
                                    if (response.body().isEmpty())
                                        Toast.makeText(getApplicationContext(), "No results. Please consider changing your search terms.", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<List<ListItem>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case MOVIE_BY_YEAR:
                            service.movieSearchReleased(searchTerm, CinemalyticsApiService.AUTH_TOKEN).enqueue(new Callback<List<ListItem>>() {
                                @Override
                                public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                                    if (response.body() == null) {
                                        Toast.makeText(getApplicationContext(), "Invalid query!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    MoviesAdapter adapter = new MoviesAdapter(response.body());
                                    searchResults.setAdapter(adapter);
                                    if (response.body().isEmpty())
                                        Toast.makeText(getApplicationContext(), "No results. Please consider changing your search terms.", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<List<ListItem>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                    }
                }
            }
        });
    }

}
