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

/**
 * The Class that has the functionality of the main page of the app.
 * It has buttons that move you to other activities and data input fields.
 * @author Sebastian Wolf
 * @version 14.12.2021
 */
public class MainActivity extends AppCompatActivity {
    EditText editedLocationtext;
    String selectedIntensity;
    ArrayList<Pin> savedPins;
    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = getIntent().getExtras().getInt("x");
        y = getIntent().getExtras().getInt("y");
        loadData();

        /* The spinner used to choose pain levels. Chosen value is stored in selectedIntensity variable */
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

    /**
     * Activates when the calendar button is pressed. Takes the variable editedLocationText and stores its String in SharedPreferences
     * if the Location of Pain EditText field is not empty.
     * Then starts CalendarActivity
     */
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
        intent.putExtra("x", x);
        intent.putExtra("y", y);
        startActivity(intent);
    }

    /**
     * Activates when the save button is pressed. Saves a new instance of Pin object to Pincushion Singleton.
     * Pin location is taken from EditText field addPainText,
     * Pin intensity is from intensitySpinner and date is either from CalendarActivity via SharedPreferences or if the SharedPreferences
     * returns null, the date is set to the current system date.
     * Finally displays a Toast to show that the information has been saved.
     */
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
            pin.setX(x);
            pin.setY(y);
            Pincushion.getInstance().addPin(pin);
            saveData();
        } else {
            Pin pin = new Pin(text, selectedIntensity, date);
            pin.setX(x);
            pin.setY(y);
            Pincushion.getInstance().addPin(pin);
            saveData();
        }
        displayToast();
    }

    /**
     * Displays a Toast
     */
    public void displayToast(){
        Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Used to save all instances of Pin in a gson-json string
     */
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Pincushion.getInstance().getAllPins());
        editor.putString("task list", json);
        editor.apply();
    }

    /**
     * Used to get all data from saved gson-json string
     */
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

    /**
     * Activates when the History button is pressed and starts HistoryActivity
     *
     */
    public void historyButtonPressed(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);

    }

    /**
     *
     * Activates when the Data button is pressed and starts Graphactivity
     */
    public void graphButtonPressed(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
}