package com.example.moviereview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviereview.Listeners.OnMovieClickListener;
import com.example.moviereview.Models.SearchArrayObjects;
import com.example.moviereview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailViewHolder> {
    Context context;
    List<SearchArrayObjects> list;
    OnMovieClickListener listener;

    public DetailsAdapter(Context context, List<SearchArrayObjects> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.tv_movie.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getBackdrop_path()).into(holder.iv_backdrop_path);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class DetailViewHolder extends RecyclerView.ViewHolder{
    ImageView iv_backdrop_path;
    TextView tv_movie;
    LinearLayout linearLayout;


    public DetailViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_backdrop_path = itemView.findViewById(R.id.iv_backdrop_path);
        tv_movie = itemView.findViewById(R.id.tv_movie);
        linearLayout = itemView.findViewById(R.id.detail_container);
    }
}