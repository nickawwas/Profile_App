package com.example.consultation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.consultation_app.database.DatabaseHelper;
import com.example.consultation_app.models.Access;
import com.example.consultation_app.models.Profile;

import java.util.List;

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

        //TODO: Delete Profile Action Menu Item
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Default Display Profile Names And Access History
        //initProfile();
    }

    private void initProfile(long profileId) {
        // Get Profile Clicked On With Given Id
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Profile profile = dbHelper.getProfileById(profileId);

        // Set Profile Data to TextView
        profileSurname.setText(getTextFromResource(R.string.profileSurname, profile.getSurname()));
        profileName.setText(getTextFromResource(R.string.profileName, profile.getStudentName()));
        profileID.setText(getTextFromResource(R.string.profileID, profile.getStudentId() + ""));
        profileGPA.setText(getTextFromResource(R.string.profileGPA, profile.getGpa() + ""));
        profileCreationDate.setText(getTextFromResource(R.string.profileCreationDate, profile.getCreationDate() + ""));

        // Get List of Accesses
        List<Access> accesses = dbHelper.getAllAccessesById(profileId);
        accessHistoryList.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, accesses));
    }


    private String getTextFromResource(int resourceId, String profileData) {
        return getResources().getString(resourceId) + " " + profileData;
    }
}