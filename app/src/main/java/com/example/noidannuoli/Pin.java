package com.example.noidannuoli;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Pin {
    String location;
    int intensity;
    Date dateOfPain;
    Time timeOfPain;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public Date getDateOfPain() {
        return dateOfPain;
    }

    public void setDateOfPain(Date dateOfPain) {
        this.dateOfPain = dateOfPain;
    }

    public Time getTimeOfPain() {
        return timeOfPain;
    }

    public void setTimeOfPain(Time timeOfPain) {
        this.timeOfPain = timeOfPain;
    }

    public Pin() {
        this.location = "";
        this.intensity = 1;
        this.dateOfPain = Calendar.getInstance().getTime();
    }
}
