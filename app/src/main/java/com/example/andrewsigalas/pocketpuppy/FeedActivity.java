package com.example.andrewsigalas.pocketpuppy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedActivity extends AppCompatActivity {

    Intent context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        String dogName =
                getSharedPreferences("DOG_DATA", Context.MODE_PRIVATE).getString("dog_name", "");

        TextView feed_message = (TextView) findViewById(R.id.feed_message);
        context = getIntent();
        String food = context.getStringExtra("feed_option");

        if (food == "Bone"){
            food = "a Bone";
        }

        feed_message.setText(dogName + " was fed with " + food + "!");
    }
    public void backToHome(View v)
    {
        context.setClass(this,DogHomepageActivity.class);
        startActivity(context);
        finish();
    }
}
