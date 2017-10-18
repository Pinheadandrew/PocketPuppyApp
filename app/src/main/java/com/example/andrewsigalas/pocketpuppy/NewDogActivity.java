package com.example.andrewsigalas.pocketpuppy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewDogActivity extends AppCompatActivity {

    String name, breed;
    int age;
    Intent caller; // Declared to test out one intent for the whole class.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dog);

        caller = getIntent();
        SharedPreferences dogData = getSharedPreferences("DOG_DATA", Context.MODE_PRIVATE);

        // Pull info from preferences to be displayed.
        name = dogData.getString("dog_name", "");
        breed = dogData.getString("breed", "");
        age = dogData.getInt("age", 0);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("Your new dog is a " + age + "-year old " + breed + " named " + name + "!");

    }
    public void continueTo(View v){
        caller.setClass(this, DogHomepageActivity.class);
        startActivity(caller);
        finish();
    }
}
