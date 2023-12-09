package com.example.moviereview.Models;

import java.util.List;

public class SearchApiResponse {
    List<SearchArrayObjects> results = null;
    public List<SearchArrayObjects> getResults() {
        return results;
    }

    public void setResults(List<SearchArrayObjects> results) {
        this.results = results;
    }
}
