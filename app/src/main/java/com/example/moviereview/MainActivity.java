package com.example.moviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moviereview.Adapters.DetailsAdapter;
import com.example.moviereview.Adapters.HomeRecyclerAdapter;
import com.example.moviereview.Listeners.OnMovieClickListener;
import com.example.moviereview.Listeners.OnSearchApiListener;
import com.example.moviereview.Models.SearchApiResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener  {
    SearchView searchView;
    RecyclerView recycler_view_home;
    HomeRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        searchView = findViewById(R.id.search_view);
        recycler_view_home = findViewById(R.id.recycler_view_home);
        manager = new RequestManager(this);
        dialog = new ProgressDialog(this);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Loading...");
                dialog.show();
                manager.searchMovies(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        searchView.setQuery("avengers",true);
    }

    private final OnSearchApiListener listener = new OnSearchApiListener() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if(response==null){
                Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                dialog.dismiss();
                showResult(response);
            }

        }

        @Override
        public void onError(String message) {
//            Log.d("error msg", message);
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An error occured!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(SearchApiResponse response) {
        recycler_view_home.setHasFixedSize(true);
        recycler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        adapter = new HomeRecyclerAdapter(this,response.getResults(),this);
        recycler_view_home.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id, String moviename, String backdroppath, String overview, String release_date, String vote_count) {
        Intent in  = new Intent(MainActivity.this, DetailsActivity.class);
        Bundle b= new Bundle();
        b.putString("moviename", moviename);
        b.putString("imageurl", backdroppath);
        b.putString("overview", overview);
        b.putString("release_date", release_date);
        b.putString("vote_count", vote_count);

        in.putExtras(b);
        startActivity(in);
    }
}