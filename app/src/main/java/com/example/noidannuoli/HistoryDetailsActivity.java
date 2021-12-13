package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class HistoryDetailsActivity extends Activity {
    Pincushion pincushion;
    int indexOfPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*0.7), (int) (height*0.5));

        pincushion = Pincushion.getInstance();

        Bundle pins = getIntent().getExtras();

        indexOfPin = pins.getInt("indexOfPin", 0);

        ((TextView) findViewById(R.id.pinDetailText)).setText(pincushion.getPin(indexOfPin).toString());
    }

    public void deleteButtonPressed(View v){
        pincushion.deletePin(indexOfPin);
        saveData();
        Intent backToHistory = new Intent(HistoryDetailsActivity.this, HistoryActivity.class);
        startActivity(backToHistory);
    }

    public void backButtonPressed(View v){
        Intent backToHistory = new Intent(HistoryDetailsActivity.this, HistoryActivity.class);
        startActivity(backToHistory);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Pincushion.getInstance().getAllPins());
        editor.putString("task list", json);
        editor.apply();
    }
}