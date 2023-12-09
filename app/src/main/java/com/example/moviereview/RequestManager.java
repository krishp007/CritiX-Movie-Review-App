package com.example.moviereview;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.moviereview.Listeners.OnSearchApiListener;
import com.example.moviereview.Models.SearchApiResponse;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class RequestManager {
    Context context;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://advanced-movie-search.p.rapidapi.com/search/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name){
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchApiResponse> call, @NonNull Response<SearchApiResponse> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }

                listener.onResponse(response.body());

            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies{
        @Headers({
                "Accept: application/json",
                "X-RapidAPI-Host: advanced-movie-search.p.rapidapi.com",
                "X-RapidAPI-Key: 6aec7f06c1msh32a7aeccf6a033cp1952dajsn1330465d9ead"
        })
        @GET("movie")
        Call<SearchApiResponse> callMovies(
                @Query("query") String movie_name
        );

    }
}

