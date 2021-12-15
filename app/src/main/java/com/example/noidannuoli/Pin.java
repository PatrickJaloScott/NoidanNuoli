package com.example.noidannuoli;

import android.graphics.Point;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class Pin {
    String location;
    String intensity;
    String dateOfPain;
    Time timeOfPain;
    int x, y;

    public Pin(String location, String intensity, String dateOfPain){
        this.location = location;
        this.intensity = intensity;
        this.dateOfPain = dateOfPain;
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

    public String getDateOfPain() {
        return dateOfPain;
    }

    public void setDateOfPain(String dateOfPain) {
        this.dateOfPain = dateOfPain;
    }

    public Time getTimeOfPain() {
        return timeOfPain;
    }

    public void setTimeOfPain(Time timeOfPain) {
        this.timeOfPain = timeOfPain;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return "  " + this.location + "\n  " + "Intensity: " + this.intensity + "\n  " + this.dateOfPain;
    }
}
