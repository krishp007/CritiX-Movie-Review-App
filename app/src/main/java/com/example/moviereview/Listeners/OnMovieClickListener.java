package com.example.moviereview.Listeners;

public interface OnMovieClickListener {
    void onMovieClicked(String id, String title, String backdrop_path, String overview, String release_date, String vote_count);
}
