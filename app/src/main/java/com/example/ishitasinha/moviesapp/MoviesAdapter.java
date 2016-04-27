package com.example.ishitasinha.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<ListItem> moviesList;
    Context context;

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView title;

        public MovieViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ListItem movieDetails = moviesList.get(getAdapterPosition());
            Log.v(MoviesAdapter.class.getSimpleName(), movieDetails.censor + " " + movieDetails.genre + " " + movieDetails.posterUrl + " " + movieDetails.releaseDate + " " + movieDetails.rating + " " + movieDetails.title);
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("TITLE", movieDetails.getTitle());
            intent.putExtra("POSTER", movieDetails.getPosterUrl());
            intent.putExtra("PLOT", movieDetails.getPlot());
            intent.putExtra("RATING", movieDetails.getRating());
            intent.putExtra("RELEASED", movieDetails.getReleaseDate());
            intent.putExtra("CENSOR", movieDetails.getCensor());
            intent.putExtra("GENRE", movieDetails.getGenre());
            context.startActivity(intent);
        }
    }

    public MoviesAdapter(List<ListItem> movies) {
        moviesList = movies;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        ListItem item = moviesList.get(position);
        holder.title.setText(item.getTitle());
        Picasso.with(context).load(item.getPosterUrl()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
