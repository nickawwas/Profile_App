package com.example.consultation_app.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultation_app.R;
import com.example.consultation_app.Controllers.DatabaseHelper;
import com.example.consultation_app.Models.Access;
import com.example.consultation_app.Models.DateTimeFormatter;
import com.example.consultation_app.Models.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    protected ListView accessHistoryList;
    protected TextView profileSurname, profileName, profileID, profileGPA, profileCreationDate;

    protected int profileId;
    protected boolean isDeleted;
    protected DatabaseHelper dbHelper;
    protected DateTimeFormatter formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Helpers - dbHelper and formatter
        dbHelper = new DatabaseHelper(this);
        formatter = new DateTimeFormatter();

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
        Bundle b = getIntent().getExtras();
        if(b != null) {
            isDeleted = false;
            profileId = b.getInt("profileId");

            // Insert Open Access to DB
            long accessId = dbHelper.insertAccess(new Access(profileId, "Opened", formatter.getDateTimeFormat()));

            // Initialize Profile Page
            initProfile(profileId);
        }
    }

    private void initProfile(int profileId) {
        // Get Profile Clicked On With Given Id
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Profile profile = dbHelper.getProfileById(profileId);

        // Set Profile Data to TextView
        profileSurname.setText(getTextFromResource(R.string.profileSurname, profile.getSurname()));
        profileName.setText(getTextFromResource(R.string.profileName, profile.getStudentName()));
        profileID.setText(getTextFromResource(R.string.profileID, profile.getProfileId() + ""));
        profileGPA.setText(getTextFromResource(R.string.profileGPA, profile.getGpa() + ""));
        profileCreationDate.setText(getTextFromResource(R.string.profileCreationDate, profile.getCreationDate() + ""));

        // Load List of Accesses
        loadAcceses(profileId);
    }

    public void loadAcceses(int profileId) {
        // Get List of Accesses
        List<Access> accesses = dbHelper.getAllAccessesById(profileId);

        // Display List of Accesses Types and Times
        List<String> accessedItems = new ArrayList<>();
        for(int i = 0; i < accesses.size(); i++) {
            Access access = accesses.get(i);
            accessedItems.add(access.getTimeStamp() + " (" + access.getAccessType() + ") ");
        }

        accessHistoryList.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, accessedItems));
    }

    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    // Select Option from Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteProfile:
                // Allow Delete if Profile Is Not Deleted, Else Disable Delete
                if(!isDeleted) {
                    // Delete Profile and Add Access
                    long deletionId = dbHelper.deleteProfile(profileId);

                    // Insert Deleted Access to Access Table
                    long accessId = dbHelper.insertAccess(new Access(profileId, "Deleted", formatter.getDateTimeFormat()));

                    // Toast Message Informing User Delete Was Successful
                    Toast.makeText(getApplicationContext(), "Student Profile Deleted!", Toast.LENGTH_LONG).show();

                    // Reload Access List
                    loadAcceses(profileId);

                    //Disable Delete Button
                    isDeleted = !isDeleted;
                } else {
                    // Toast Message Informing User Already Delete
                    Toast.makeText(getApplicationContext(), "Student Profile Already Deleted!", Toast.LENGTH_LONG).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // On Back Press, A Closed Event is Added to Access Table
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Add Closed Access to DB
        long accessId = dbHelper.insertAccess(new Access(profileId, "Closed", formatter.getDateTimeFormat()));
    }

    private String getTextFromResource(int resourceId, String profileData) {
        return getResources().getString(resourceId) + " " + profileData;
    }
}