package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText editedLocationtext;
    EditText editedPainText;
    String selectedIntensity;
    Spinner intensitySpinner;
    ArrayList<Pin> savedPins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        Spinner intensitySpinner = (Spinner) findViewById(R.id.spinner_intensity);

        final List<String> intensityLevelsList = new ArrayList<>();
        intensityLevelsList.add("1");
        intensityLevelsList.add("2");
        intensityLevelsList.add("3");
        intensityLevelsList.add("4");
        intensityLevelsList.add("5");

        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,intensityLevelsList);
        intensitySpinner.setAdapter(adapter);

        intensitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void saveClick(View v) {
        editedLocationtext = (EditText) findViewById(R.id.addPainText);
        String text = editedLocationtext.getText().toString();
        editedPainText = (EditText) findViewById(R.id.painIntensityText);
        String intens = editedPainText.getText().toString();
        Pin pin = new Pin(text,intens);
        Pincushion.getInstance().addPin(pin);
        saveData();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Pincushion.getInstance().getAllPins());
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Pin>>() {}.getType();
        savedPins = gson.fromJson(json, type);

        if (savedPins == null) {
            savedPins = new ArrayList<>();
        }
        Pincushion.getInstance().setPins(savedPins);
    }

    public void historyButtonPressed(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
    /*public void changeToTest(View view) {
        Intent intent = new Intent(this, TestPinActivity.class);
        startActivity(intent);
    }*/
}