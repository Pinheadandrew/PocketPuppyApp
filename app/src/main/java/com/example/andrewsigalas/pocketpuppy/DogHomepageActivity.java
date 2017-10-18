package com.example.andrewsigalas.pocketpuppy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/* Handles control flow of activities behind the dog home page. Options for user to interact with
   dog from hear, with tasks such as:
    1. Walking
    2. Feeding
    3. Renaming the dog
 */
public class DogHomepageActivity extends AppCompatActivity {

    TextView headerView, selectedWalkOption, selectedFoodOption;
    int age;
    ImageView dogImage;
    String name, breed;
    String walkExtra, foodExtra;
    SharedPreferences dogData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_homepage);

        // Opens up the preferences file and collect the fields (name, age, breed).
        dogData = getSharedPreferences("DOG_DATA", Context.MODE_PRIVATE);
        name = dogData.getString("dog_name", "");
        breed = dogData.getString("breed", "none");
        age = dogData.getInt("age", 0);

        // Collect the views to be dynamically displayed
        headerView = (TextView) findViewById(R.id.headerView);
        dogImage = (ImageView) findViewById(R.id.dogImage);
        selectedWalkOption = (TextView) findViewById(R.id.selectedWalkOption);
        selectedFoodOption = (TextView) findViewById(R.id.selectedFoodOption);
        ListView walkList = (ListView) findViewById(R.id.walkOptions);
        ListView foodList = (ListView) findViewById(R.id.foodOptions);

        headerView.setText(name + ", " + age);

        // Set the image according to the breed selected.
        switch (breed)
        {
            case "Golden Retriever":
                dogImage.setImageResource(R.drawable.golden_retriever);
                break;
            case "Maltese":
                dogImage.setImageResource(R.drawable.maltese);
                break;
            case "Boxer":
                dogImage.setImageResource(R.drawable.boxer);
                break;
            case "Yorkshire Terrier":
                dogImage.setImageResource(R.drawable.yorky);
                break;
            case "German Shepherd":
                dogImage.setImageResource(R.drawable.german_shepherd);
                break;
            default:
                dogImage.setImageResource(R.drawable.golden_retriever);
                break;
        }

        String[] walkTimes = {"15 minutes", "30 minutes", "1 hour"};
        final ArrayAdapter<String> walkOptions = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, walkTimes);

        walkList.setAdapter(walkOptions);
        walkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                walkExtra = walkOptions.getItem(position).toString();
                selectedWalkOption.setText("Walk for " + walkExtra);
            }
        });

        // Populate the items of the food options ListView.
        String[] treats = {"Dog food", "Bone", "Leftover meat"};
        final ArrayAdapter<String> foodOptions = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, treats);

        foodList.setAdapter(foodOptions);
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                foodExtra = foodOptions.getItem(position).toString();
                selectedFoodOption.setText("Feed " + foodOptions.getItem(position));
            }
        });
    }

    public void changeName(View v)
    {
        // Set dog_name extra to the name written from the editText and repass into headerView.
        String new_name = ((EditText) findViewById(R.id.renameButton)).getText().toString();
        dogData.edit().putString("dog_name", new_name).apply();
        name = dogData.getString("dog_name", "");

        headerView.setText( name + ", " + age);
        Toast.makeText(getApplicationContext(),
                "Name changed.",
                Toast.LENGTH_SHORT).show();
    }
    public void walk(View v)
    {
        Intent walk = new Intent();
        walk.putExtra("walk_option", walkExtra);
        walk.setClass(this, WalkActivity.class);
        startActivity(walk);
    }
    public void feed(View v)
    {
        Intent feed = new Intent();
        feed.putExtra("feed_option", foodExtra);
        feed.setClass(this, FeedActivity.class);
        startActivity(feed);
    }
}
