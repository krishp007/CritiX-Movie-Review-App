package com.example.moviereview.Listeners;

import com.example.moviereview.Models.SearchApiResponse;

public interface OnSearchApiListener {
    void onResponse(SearchApiResponse response);
    void onError(String message);

}
