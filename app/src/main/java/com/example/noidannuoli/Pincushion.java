package com.example.noidannuoli;

import java.util.ArrayList;
import java.util.Collections;

public class Pincushion {
    private static final Pincushion pinInstance = new Pincushion();

    public static Pincushion getInstance(){
        return pinInstance;
    }

    private ArrayList<Pin> pins;

    private Pincushion(){
        pins = new ArrayList<>();
    }

    public void setPins(ArrayList<Pin> pins) {
        this.pins = pins;
    }

    public ArrayList<Pin> getAllPins(){
        return this.pins;
    }

    public Pin getPin(int indexOfPin){
        return this.pins.get(indexOfPin);
    }

    public void deletePin(int indexOfPin){
        this.pins.remove(indexOfPin);
    }

    public void addPin(Pin pin){
        this.pins.add(pin);
    }

    public void removePin(Pin pin){
        this.pins.remove(pin);
    }
}
