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

}