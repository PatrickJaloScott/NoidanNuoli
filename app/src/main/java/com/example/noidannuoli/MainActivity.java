package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText editedLocationtext;
    String selectedIntensity;
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
                ((TextView) parent.getChildAt(0)).setTextSize(24);
                selectedIntensity = intensitySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("info of pain", MODE_PRIVATE);
        editedLocationtext = (EditText) findViewById(R.id.addPainText);
        editedLocationtext.setText(sharedPreferences.getString("text of pain", ""));

    }

    public void calendarButtonPressed(View v) {
        editedLocationtext = (EditText) findViewById(R.id.addPainText);
        String checkText = editedLocationtext.getText().toString();
        if (!(TextUtils.isEmpty(checkText))){
            SharedPreferences sharedPreferences = getSharedPreferences("info of pain",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("text of pain", checkText);
            editor.apply();
        }
        Intent intent = new Intent (MainActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void saveClick(View v) {
        editedLocationtext = (EditText) findViewById(R.id.addPainText);
        String text = editedLocationtext.getText().toString();
        Intent dateFromCalendar = getIntent();
        String date = dateFromCalendar.getStringExtra("date");
        if (date == null) {
            long dateLong = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateD = new Date(dateLong);
            String dateToday = simpleDateFormat.format(dateD);
            Pin pin = new Pin(text, selectedIntensity, dateToday);
            Pincushion.getInstance().addPin(pin);
            saveData();
        } else {
            Pin pin = new Pin(text, selectedIntensity, date);
            Pincushion.getInstance().addPin(pin);
            saveData();
        }
        displayToast();
    }

    public void displayToast(){
        Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
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
}