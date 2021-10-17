package com.example.profile_app.Models;

public class Access {
    private int id;
    private int profileId;
    private String type;
    private String timeStamp;

    // Default Constructor - Access id Auto Incremented
    public Access(int pid, String accessType, String accessTime) {
        profileId = pid;
        type = accessType;
        timeStamp = accessTime;
    }

    // Regular Constructor
    public Access(int aid, int pid, String accessType, String accessTime) {
        id = aid;
        profileId = pid;
        type = accessType;
        timeStamp = accessTime;
    }

    public int getProfileID() {
        return profileId;
    }

    public String getAccessType() {
        return type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
