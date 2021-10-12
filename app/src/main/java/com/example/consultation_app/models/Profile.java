package com.example.consultation_app.models;

import java.util.Date;

public class Profile {
    private long id;
    private String surname;
    private String name;
    private int studentId;
    private double gpa;
    private Date creationDate;

    // Constructor
    public Profile(long pid, String profileSurname, String profileName, int profileSid, double profileGpa, Date profileCreation) {
        id = pid;
        surname = profileSurname;
        name = profileName;
        studentId = profileSid;
        gpa = profileGpa;
        creationDate = profileCreation;
    }

    public String getSurname() {
       return surname;
    }

    public String getStudentName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
