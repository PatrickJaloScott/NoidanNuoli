package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", "1");
        Type type = new TypeToken<ArrayList<Pin>>() {}.getType();
        pinList = gson.fromJson(json, type);
    }
}