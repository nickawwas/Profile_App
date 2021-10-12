package com.example.consultation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.consultation_app.database.DatabaseHelper;
import com.example.consultation_app.models.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected TextView totalProfilesCount;
    protected ListView profileList;
    protected FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Buttons to Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create Object & Initialize Total Profile Count
        totalProfilesCount = findViewById(R.id.mainProfileCount);

        // Create ListView of Profiles & Load Profiles
        profileList = findViewById(R.id.profileDataList);
        loadProfileListView(true);

        profileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Determine List Element Then Pass Element to Profile Page
                goToProfileActivity();
            }
        });

        // Floating Action Button -> Open Dialog Fragment
        floatingActionButton = findViewById(R.id.insertProfileButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Dialog Fragment
                InsertProfileDialogFragment dialog = new InsertProfileDialogFragment();
                dialog.show(getSupportFragmentManager(), "InsertProfileFragment");
            }
        });
    }

    // TODO: Delete?
    @Override
    protected void onStart() {
        super.onStart();

        //If ListView Length != Database Table Length
        // Update List View of Profiles
    }

    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Select Option from Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toggle Between Profile Name And Id
        switch (item.getItemId()) {
            case R.id.toggleProfilesById:
                // Query Data Ordered By Id
                loadProfileListView(false);
                return true;
            case R.id.toggleProfilesByName:
                // Query Data Ordered By Name
                loadProfileListView(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Navigation to Profile Activity
    private void goToProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    // Get, Initialize, and Update Total Count Text
    protected void loadProfileListView(boolean orderByName) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Profile> profiles = dbHelper.getAllProfiles(orderByName);

        // Add Profile Entries to ListView
        profileList.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, profiles));

        // Add Number of Entries to Textview
        totalProfilesCount.setText(profiles.size() + " " + getResources().getString(R.string.profileNumbers) + " " + getResources().getString(orderByName ? R.string.profileSortedByName : R.string.profileSortedById));
    }
}