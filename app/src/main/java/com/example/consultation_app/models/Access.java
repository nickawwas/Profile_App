package com.example.consultation_app.models;

import java.util.Date;

public class Access {
    private long id;
    private long profileId;
    private String type;
    private Date timeStamp;

    // Constructor
    public Access(long aid, long pid, String accessType, Date accessTime) {
        id = aid;
        profileId = pid;
        type = accessType;
        timeStamp = accessTime;
    }

    public long getProfileID() {
        return profileId;
    }

    public String getAccessType() {
        return type;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
