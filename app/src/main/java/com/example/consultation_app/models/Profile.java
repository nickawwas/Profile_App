package com.example.consultation_app.models;

public class Profile {
    private long id;
    private String surname;
    private String name;
    private int studentId;
    private double gpa;

    // Constructor
    public Profile(long pid, String profileSurname, String profileName, int profileSid, double profileGpa) {
        id = pid;
        surname = profileSurname;
        name = profileName;
        studentId = profileSid;
        gpa = profileGpa;
    }

//    public long getPID() {
//        return id;
//    }

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
}
