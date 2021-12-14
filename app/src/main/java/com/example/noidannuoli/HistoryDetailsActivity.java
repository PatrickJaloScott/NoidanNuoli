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

/**
 * The Class of an activity that opens when a specific Pin is clicked on in HistoryActivity's ListView
 * and shows the information stored in the instance. Also has functionality to delete the chosen Pin
 * @author Sebastian Wolf
 * @version 14.12.2021
 */
public class HistoryDetailsActivity extends Activity {
    Pincushion pincushion;
    int indexOfPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        /* Here we make the activity's window smaller so it looks like pop up window*/
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

    /**
     * Activates when Delete button is pressed and deletes the chosen Pin and saves the new list
     * Then goes to HistoryActivity
     */
    public void deleteButtonPressed(View v){
        pincushion.deletePin(indexOfPin);
        saveData();
        Intent backToHistory = new Intent(HistoryDetailsActivity.this, HistoryActivity.class);
        startActivity(backToHistory);
    }

    /**
     * Activates when the Back button is pressed and returns the view to ActivityHistory
     */
    public void backButtonPressed(View v){
        Intent backToHistory = new Intent(HistoryDetailsActivity.this, HistoryActivity.class);
        startActivity(backToHistory);
    }

    /**
     * Used to save the current Pincushion to json-gson
     */
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Pincushion.getInstance().getAllPins());
        editor.putString("task list", json);
        editor.apply();
    }
}