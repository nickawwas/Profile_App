package com.example.profile_app.Views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profile_app.R;
import com.example.profile_app.Controllers.DatabaseHelper;
import com.example.profile_app.Models.Access;
import com.example.profile_app.Models.DateTimeFormatter;
import com.example.profile_app.Models.Profile;

// Dialog Fragment
public class InsertProfileDialogFragment extends DialogFragment {
    protected Button cancelButton, saveButton;
    protected EditText profileSurnameInput, profileNameInput, profileStudentIDInput, profileGPAInput;

    protected DatabaseHelper dbHelper;
    protected DateTimeFormatter formatter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        //Initialize Helpers
        dbHelper = new DatabaseHelper(getActivity());
        formatter = new DateTimeFormatter();

        // Input Fields for Student Profile Data
        profileSurnameInput = view.findViewById(R.id.dialogSurnameInput);
        profileNameInput = view.findViewById(R.id.dialogNameInput);
        profileStudentIDInput = view.findViewById(R.id.dialogIDInput);
        profileGPAInput = view.findViewById(R.id.dialogGPAInput);

        //Create Object and Listener for Cancel and Save Buttons
        cancelButton = view.findViewById(R.id.cancelProfileButton);
        saveButton = view.findViewById(R.id.saveProfileButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Input Responses
                String surname = getInputText(profileSurnameInput);
                String name = getInputText(profileNameInput);
                String id = getInputText(profileStudentIDInput);
                String gpa = getInputText(profileGPAInput);

                // Validate Inputs
                // 1) All Inputs Must Be Filled
                if (surname.isEmpty() || name.isEmpty() || id.isEmpty() || gpa.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Must Fill All Input Fields!", Toast.LENGTH_LONG).show();
                } else {
                    int idNum = Integer.parseInt(id);
                    double gpaNum = Double.parseDouble(gpa);

                    // 2) Id Must Be Exactly 8 Characters Long
                    if (id.length() != 8) {
                        Toast.makeText(getActivity().getApplicationContext(), "IDs Must Be Exactly 8 Numbers Long!", Toast.LENGTH_LONG).show();
                        // 3) Id Must Be Between 1000 0000 and 9999 9999
                    } else if (idNum < 10000000 || idNum > 99999999) {
                        Toast.makeText(getActivity().getApplicationContext(), "IDs Must Be Exactly 8 Numbers Long!", Toast.LENGTH_LONG).show();
                        // 4) Id Must Be Unique, Cannot Already Exist in DB
                    } else if (dbHelper.getProfileById(idNum) != null) {
                        Toast.makeText(getActivity().getApplicationContext(), "User with Inputted ID Already Exists!", Toast.LENGTH_LONG).show();
                    } else {
                        //5) GPA Must Be Between 0.0 and 4.3
                        if (gpaNum < 0.0 || gpaNum > 4.3) {
                            Toast.makeText(getActivity().getApplicationContext(), "GPA Must Be Between 0.0 and 4.3!", Toast.LENGTH_LONG).show();
                        } else {
                            // Format and Convert Current Date and Time
                            String dateTimeStr = formatter.getDateTimeFormat();

                            // Insert Data into Profile and Access Tables
                            long profileId = dbHelper.insertProfile(new Profile(idNum, surname, name, gpaNum, dateTimeStr));
                            long accessId = dbHelper.insertAccess(new Access(idNum, "Created", dateTimeStr));

                            // Check ProfileId and AccessId to Ensure Proper Insertion of Profile and Access in DB
                            if(profileId != -1 && accessId != -1) {
                                Toast.makeText(getActivity().getApplicationContext(), "Student Profile Saved!", Toast.LENGTH_LONG).show();

                                // Reload Profile Data
                                ((MainActivity) getActivity()).loadProfileListView(true);

                                // Close Dialog Fragment
                                dismiss();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Student Profile Failed to Save... Try Again!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });

        return view;
    }

    // Get Input Name Text
    private String getInputText(EditText inputElement) {
        return inputElement.getText().toString();
    }
}