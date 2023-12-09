package com.example.moviereview.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviereview.Listeners.OnMovieClickListener;
import com.example.moviereview.MainActivity;
import com.example.moviereview.Models.SearchArrayObjects;
import com.example.moviereview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder>{
    Context context;
    List<SearchArrayObjects> list;
    OnMovieClickListener listener;

    public HomeRecyclerAdapter(Context context, List<SearchArrayObjects> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movies_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_movie.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getPoster_path()).into(holder.iv_poster);

        holder.home_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(list.get(position).getId(), list.get(position).getTitle(), list.get(position).getBackdrop_path(),
                        list.get(position).getOverview(), list.get(position).getRelease_date(), list.get(position).getVote_average());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class HomeViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_poster;
    TextView tv_movie;
    CardView home_container;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_poster = itemView.findViewById(R.id.iv_poster);
        tv_movie = itemView.findViewById(R.id.tv_movie);
        home_container = itemView.findViewById(R.id.home_container);
    }
}
