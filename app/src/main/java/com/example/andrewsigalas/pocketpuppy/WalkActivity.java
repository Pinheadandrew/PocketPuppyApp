package com.example.andrewsigalas.pocketpuppy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WalkActivity extends AppCompatActivity {

    Intent context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        context = getIntent();
        String dogName =
                getSharedPreferences("DOG_DATA", Context.MODE_PRIVATE).getString("dog_name", "");

        String walkTime = context.getStringExtra("walk_option");
        TextView walk_message = (TextView) findViewById(R.id.walk_message);
        walk_message.setText(dogName + " was walked for " + walkTime + ". Now it's naptime!");
    }
    public void backToHome(View v)
    {
        context.setClass(this,DogHomepageActivity.class);
        startActivity(context);
        finish();
    }
}
