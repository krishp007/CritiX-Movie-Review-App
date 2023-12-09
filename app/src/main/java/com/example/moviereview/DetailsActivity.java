package com.example.moviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.moviereview.Adapters.DetailsAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    DetailsAdapter detailsAdapter;
    TextView movieTitleView;
    TextView overview;
    TextView vote_count;
    TextView release_date;
    ImageView headerImageView;
    RatingBar ratingBar;
    Button rate_button;
    Button premium;
    LottieAnimationView lottietick;
    ExpandableTextView expandableTextView;
    float myRating = 0;
    private boolean btntick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();

        movieTitleView = findViewById(R.id.tv_movie);
        headerImageView = findViewById(R.id.iv_backdrop_path);
        overview = findViewById(R.id.tv_overview);
        vote_count = findViewById(R.id.tv_vote_count);
        release_date = findViewById(R.id.tv_release_date);
        expandableTextView = findViewById(R.id.expandable_text);

        Intent in = getIntent();

        Bundle b = in.getExtras();

        String movieName = b.getString("moviename");
        String imageUrl = b.getString("imageurl");
        String overviewDetail = b.getString("overview");
        String vote_countDetail = b.getString("vote_count");
        String release_dateDetail = b.getString("release_date");

        movieTitleView.setText(movieName);
        overview.setText(overviewDetail);
        expandableTextView.setText((CharSequence) overview);
        vote_count.setText(vote_countDetail);
        release_date.setText(release_dateDetail);
        Picasso.get().load(imageUrl).into(headerImageView);

        ratingBar = findViewById(R.id.rating);
        rate_button = findViewById(R.id.rate_button);
        lottietick = findViewById(R.id.lottietick);

        lottietick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btntick) {
                    lottietick.playAnimation();
//                    lottietick.pauseAnimation();
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myRating = ratingBar.getRating();
            }
        });

        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "You rated " + String.valueOf(myRating) + "/10", Toast.LENGTH_SHORT).show();
            }
        });

        premium = findViewById(R.id.btn_premium);
        premium.setOnClickListener(new View.OnClickListener() {
            Intent in;
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, PremiumAppActivity.class));
            }
        });
    }
}