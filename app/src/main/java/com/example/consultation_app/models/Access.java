package com.example.consultation_app.models;

public class Access {
    private int id;
    private int profileId;
    private String type;
    private String timeStamp;

    // Constructor
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
