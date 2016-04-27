package com.example.ishitasinha.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ishitasinha on 15/04/16.
 */
public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {

    List<ActorPojo> actorsList;
    Context context;

    public ActorsAdapter(List<ActorPojo> actors) {
        actorsList = actors;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ActorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        ActorPojo actor = actorsList.get(position);
        holder.title.setText(actor.getName());
        Picasso.with(context).load(actor.getPicture()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return actorsList.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;

        public ActorViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
