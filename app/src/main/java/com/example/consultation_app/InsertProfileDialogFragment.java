package com.example.consultation_app;

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

import com.example.consultation_app.database.DatabaseHelper;
import com.example.consultation_app.models.Access;
import com.example.consultation_app.models.Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

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
                dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize Database Helper
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

                // Get Input Responses
                String surname = getInputText(profileSurnameInput);
                String name = getInputText(profileNameInput);
                String id = getInputText(profileStudentIDInput);
                String gpa = getInputText(profileGPAInput);

                int idNum = Integer.parseInt(id);
                double gpaNum = Double.parseDouble(gpa);

                // Validate Inputs
                // 1) All Inputs Must Be Filled
                if (surname.isEmpty() || name.isEmpty() || id.isEmpty() || gpa.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Must Fill All Input Fields!", Toast.LENGTH_LONG).show();
                } else {
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
                            Date creationDate = new Date();
                            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd @ HH:mm:ss");

                            // Insert Data into Profile and Access Tables
                            long profileId = dbHelper.insertProfile(new Profile(idNum, surname, name, gpaNum, dateTimeFormat.format(creationDate)));
                            long accessId = dbHelper.insertAccess(new Access(idNum, "CREATED", dateTimeFormat.format(creationDate)));

                            Toast.makeText(getActivity().getApplicationContext(), "Student Profile Saved!", Toast.LENGTH_LONG).show();
                            dismiss();
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