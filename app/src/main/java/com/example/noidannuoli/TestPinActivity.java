package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.List;

public class TestPinActivity extends AppCompatActivity {

    public static List<Pin> pinCushion;
    private Pin pinAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pin);

        Intent intent = getIntent();
    }

    public void setPinDate(View view) {
        Log.i("my app","click?");
    }

    public void setPinTime() {

    }

    /*public void addPin(View view) {
        EditText editText = findViewById(R.id.editTextTextPinLocation);
        pinAddition.setLocation(editText.getText().toString());
        editText = findViewById(R.id.editTextIntensity);
        pinAddition.setIntensity(Integer.parseInt(editText.getText().toString()));
        CalendarView calendarView = findViewById(R.id.calendarView);
        pinAddition.setDateOfPain(calendarView.getDate());

        pinCushion.add(pinAddition);
        //Intent intent = new Intent(this, MainActivity.class);
        finish();
    }*/
}