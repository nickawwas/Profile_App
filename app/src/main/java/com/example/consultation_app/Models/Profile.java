package com.example.consultation_app.Models;

public class Profile {
    private int id;
    private String surname;
    private String name;
    private double gpa;
    private String creationDate;

    // Constructor
    public Profile(int pid, String profileSurname, String profileName, double profileGpa, String profileCreation) {
        id = pid;
        surname = profileSurname;
        name = profileName;
        gpa = profileGpa;
        creationDate = profileCreation;
    }

    public int getProfileId() {
        return id;
    }

    public String getSurname() {
       return surname;
    }

    public String getStudentName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
