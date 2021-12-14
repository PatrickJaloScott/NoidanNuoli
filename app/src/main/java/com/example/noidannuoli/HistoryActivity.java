package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class that shows all saved objects of Pin in Pincushion's Arraylist in a ListView format
 * @author Sebastian Wolf
 * @version 14.12.2021
 */
public class HistoryActivity extends AppCompatActivity {
    ArrayList<Pin> pinList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView lv = findViewById(R.id.listViewPins);

        pinList = new ArrayList<>();

        loadData();

        lv.setAdapter(new ArrayAdapter<Pin>(this, android.R.layout.simple_expandable_list_item_1, pinList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent historyDetailsActivity = new Intent(HistoryActivity.this, HistoryDetailsActivity.class);
                historyDetailsActivity.putExtra("indexOfPin", position);
                startActivity(historyDetailsActivity);
            }
        });
    }

    /**
     * Used to refresh the ListView when user returns to Activity from HistoryDetailsActivity
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    /**
     * Used to get all data from saved gson-json string
     */
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", "1");
        Type type = new TypeToken<ArrayList<Pin>>() {}.getType();
        pinList = gson.fromJson(json, type);
    }
}