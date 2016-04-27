package com.example.ishitasinha.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView poster;
    RatingBar rating;
    TextView title;
    TextView releaseDate;
    TextView censorRating;
    TextView genre;
    TextView plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        censorRating = (TextView) findViewById(R.id.censor_rating);
        genre = (TextView) findViewById(R.id.genre);
        plot = (TextView) findViewById(R.id.description);
        poster = (ImageView) findViewById(R.id.detail_poster);
        rating = (RatingBar) findViewById(R.id.rating);
        releaseDate = (TextView) findViewById(R.id.release_date);
        title = (TextView) findViewById(R.id.detail_title);

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("TITLE"));
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("POSTER")).into(poster);
        censorRating.setText("Censor rating: " + intent.getStringExtra("CENSOR"));
        rating.setRating(Float.parseFloat(intent.getStringExtra("RATING")));
        genre.setText("Genre: "+intent.getStringExtra("GENRE"));
        releaseDate.setText("Released on: "+intent.getStringExtra("RELEASED"));
        plot.setText(intent.getStringExtra("PLOT"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
