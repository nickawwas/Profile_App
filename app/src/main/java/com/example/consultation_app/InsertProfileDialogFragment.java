package com.example.consultation_app;

import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Dialog Fragment
public class InsertProfileDialogFragment extends DialogFragment {
    protected Button cancelButton, saveButton;
    protected EditText profileSurnameInput, profileNameInput, profileStudentIDInput, profileGPAInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

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
                goToMainActivity();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surname = getInputText(profileSurnameInput);
                String name = getInputText(profileNameInput);
                String id = getInputText(profileStudentIDInput);
                String gpa = getInputText(profileGPAInput);

                // Validate Inputs - All Inputs Must Be Filled
//                if (surname.isEmpty() || name.isEmpty() || id.isEmpty() || gpa.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Must Fill All Input Fields!", Toast.LENGTH_LONG).show();
//                } else {
//
//                    // Id Must Be Exactly 8 Characters Long
//                    if (id.length() < 8) {
//                        Toast.makeText(getApplicationContext(), "IDs Must Be Exactly 8 Numbers Long!", Toast.LENGTH_LONG).show();
//                        //TODO: Check if ID Exists in DB
//                    } else if (id != "0101 0101") {
//                        Toast.makeText(getApplicationContext(), "User with Inputted ID Already Exists!", Toast.LENGTH_LONG).show();
//                    } else {
//
//                        //Check GPA - < 4.3 & Has First Number Not Period
//                        if (gpa != "4.3") {
//                            Toast.makeText(getApplicationContext(), "GPA Must Be Between 0.0 and 4.3!", Toast.LENGTH_LONG).show();
//                        } else {
//                            // TODO: Insert Data into Table
//                            Toast.makeText(getApplicationContext(), "Student Profile Saved!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
            }

        });

        return view;
    }

    // Get Counter Input Name Text
    private String getInputText(EditText counterElement) {
        return counterElement.getText().toString();
    }

    // Navigation to Main Activity
    private void goToMainActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }
}