package com.example.profile_app.Models;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTimeFormatter {
    // Blank Constructor
    public DateTimeFormatter() { };

    public String getDateTimeFormat() {
        // Format and Convert Current Date and Time
        Date date = new Date();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd @ HH:mm:ss");

        // Format Date with Date Time Provided
        return dateTimeFormat.format(date);
    }
}
