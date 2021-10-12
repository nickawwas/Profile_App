package com.example.consultation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.consultation_app.database.AppDB;

public class ProfileActivity extends AppCompatActivity {

    protected ListView accessHistoryList;
    protected TextView profileSurname, profileName, profileID, profileGPA, profileCreationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Add Buttons to Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Profile Data
        profileSurname = findViewById(R.id.profileSurname);
        profileName = findViewById(R.id.profileName);
        profileID = findViewById(R.id.profileID);
        profileGPA = findViewById(R.id.profileGPA);
        profileCreationDate = findViewById(R.id.profileCreationDate);

        // Scrollable List of Accesses
        accessHistoryList = findViewById(R.id.accessHistoryList);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Default Display Profile Names And Access History
        initProfile();
    }

    private void initProfile() {
        // Get Profile Surname, Name, Id, GPA, Creation Date
        //TODO: Get From DB
        String surname = "";
        String name = "";
        String id = "";
        String gpa = "";
        String creationDate = "";

        // Set Profile Data
        profileSurname.setText(getTextFromResource(R.string.profileSurname, surname));
        profileName.setText(getTextFromResource(R.string.profileName, name));
        profileID.setText(getTextFromResource(R.string.profileID, id));
        profileGPA.setText(getTextFromResource(R.string.profileGPA, gpa));
        profileCreationDate.setText(getTextFromResource(R.string.profileCreationDate, creationDate));


        // Get List of Accesses - TODO: Get From DB
        //accessHistoryList.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,
        //);
    }


    private String getTextFromResource(int resourceId, String profileData) {
        return getResources().getString(resourceId) + " " + profileData;
    }
}