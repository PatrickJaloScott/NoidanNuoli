package com.example.noidannuoli;

import java.util.ArrayList;

public class Pincushion {
    private ArrayList<Pin> pins;
    private static final Pincushion thisInstance = new Pincushion();

    public static Pincushion getInstance(){
        return thisInstance;
    }
    private Pincushion(){
        this.pins = new ArrayList<Pin>();
    }
    ArrayList<Pin> getPins(){
        return this.pins;
    }
    public void add(Pin pin){
        this.pins.add(pin);
    }
    public void remove(Pin pin){
        this.pins.remove(pin);
    }
}
