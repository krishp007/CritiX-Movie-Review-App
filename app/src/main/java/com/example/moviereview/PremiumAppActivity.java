package com.example.moviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PremiumAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_app);
        getSupportActionBar().hide();
    }
}