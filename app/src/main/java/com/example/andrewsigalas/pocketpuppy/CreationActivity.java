package com.example.andrewsigalas.pocketpuppy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class CreationActivity extends AppCompatActivity {

    /* Practice declaring views on activity's instantiation. */
    ListView breedList;
    String selectedBreed = "none";
    ArrayAdapter<CharSequence> adapterBreeds;
    Intent goToSecond;
    SharedPreferences dogData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        goToSecond = new Intent();
        dogData = getSharedPreferences("DOG_DATA", Context.MODE_PRIVATE);

        // Upon app startup, if a dog has been created, the app will be directed to the dog's homepage.
        if (dogData.contains("dog_name")){
            goToSecond.setClass(this, DogHomepageActivity.class);
            startActivity(goToSecond);
            finish();
        }
        else
        {
        /* Took out the final keywords when assigning global view variables (breedList, selectedBreed, and
           and */
            breedList = (ListView) findViewById(R.id.listViewBreeds);

            adapterBreeds = ArrayAdapter.createFromResource(this,
                    R.array.dog_breed_array,
                    android.R.layout.simple_list_item_single_choice);

            breedList.setAdapter(adapterBreeds);
            breedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    selectedBreed = "" + adapterBreeds.getItem(position);
                    Toast.makeText(getApplicationContext(),
                            selectedBreed + " selected.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void create(View v) //Set preference attributes and pass
    {
        SharedPreferences.Editor dataInput = dogData.edit();
        String name = ((EditText) findViewById(R.id.editText_name)).getText().toString();
        int age = Integer.parseInt((
                (EditText) findViewById(R.id.editText_age)).getText().toString());

        // Saves the fields to the preferences file which will be opened on the app.
        dataInput.putString("dog_name", name);
        dataInput.putInt("age", age);
        dataInput.putString("breed", selectedBreed);
        dataInput.apply();

        goToSecond.setClass(this, NewDogActivity.class);
        startActivity(goToSecond);
        finish();
    }
}
