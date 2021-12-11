package com.example.noidannuoli;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Pin {
    String location;
    String intensity;
    long dateOfPain;
    Time timeOfPain;

    public Pin(String location, String intensity){
        this.location = location;
        this.intensity = intensity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public long getDateOfPain() {
        return dateOfPain;
    }

    public void setDateOfPain(long dateOfPain) {
        this.dateOfPain = dateOfPain;
    }

    public Time getTimeOfPain() {
        return timeOfPain;
    }

    public void setTimeOfPain(Time timeOfPain) {
        this.timeOfPain = timeOfPain;
    }

    /*public Pin() {
        this.location = "";
        this.intensity = 1;
        this.dateOfPain = Calendar.getInstance().getTime().getTime();
    }*/

    public String toString(){
        return this.location + "\n" + "Intensity: " + this.intensity;
    }
}
