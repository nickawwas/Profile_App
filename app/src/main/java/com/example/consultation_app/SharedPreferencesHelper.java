package com.example.consultation_app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    public  SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("EventPreference", Context.MODE_PRIVATE);
    }

    // Set & Get App Initialization Status (Counters Defined & Saved)
    public void setAppInitStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("appInitStatus", true);
        editor.commit();
    }

    public boolean getAppInitStatus() {
        return sharedPreferences.getBoolean("appInitStatus", false);
    }

    // Set & Get Settings Change
    public void setChangedSettingsState(boolean changedState) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("changedSettings", changedState);
        editor.commit();
    }

    public boolean getChangedSettingsState() {
        return sharedPreferences.getBoolean("changedSettings", false);
    }

    // Update & Get Counter Names
    public void updateCounterName(String counterId, String newCounterName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counterName" + counterId, newCounterName);
        editor.commit();
    }

    public String getCounterName(String counterId) {
        return sharedPreferences.getString("counterName" + counterId, null);
    }

    // Increment & Get Counter Counts
    public void incCounterCount(String counterId, int currCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counterCount" + counterId,  currCount + 1);
        editor.commit();
    }

    public int getCounterCount(String counterId) {
        return sharedPreferences.getInt("counterCount" + counterId, 0);
    }

    // Set & Get Data Mode (Event Name or Button #)
    public void setDataMode(boolean isEventNameMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("dataMode", isEventNameMode);
        editor.commit();
    }

    public boolean getDataMode() {
        return sharedPreferences.getBoolean("dataMode", false);
    }

    // Update & Get Max Count
    public void updateMaxCount(int maxCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("maxCount", maxCount);
        editor.commit();
    }

    public int getMaxCount() {
        return sharedPreferences.getInt("maxCount", 0);
    }

    // Reset, Increment & Get Total Count
    public void incTotalCount() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalCount", getTotalCount() + 1);
        editor.commit();
    }

    public void resetTotalCount() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalCount", 0);
        editor.putInt("counterCountA", 0);
        editor.putInt("counterCountB", 0);
        editor.putInt("counterCountC", 0);
        editor.commit();
    }

    public int getTotalCount() {
        return sharedPreferences.getInt("totalCount", 0);
    }
}
