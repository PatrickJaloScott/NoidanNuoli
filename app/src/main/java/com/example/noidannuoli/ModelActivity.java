package com.example.noidannuoli;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

// @author Janice Aaltonen
// the intended main activity - a human image/background to poke pin/arrow images in, with a button to view the history activity

public class ModelActivity extends AppCompatActivity {
    Pincushion pincushion;
    ConstraintLayout bg;
    Drawable arrow, human;
    Button history;
    ArrayList<Pin> savedPins;
    View.OnTouchListener touched;
    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_test);

        loadData();

        history = findViewById(R.id.buttonHistory);
        arrow = getDrawable(R.drawable.ic_arrowpin);
        bg = findViewById(R.id.constLout);
        if (bg.getBackground() == null){
            human = AppCompatResources.getDrawable(this, R.drawable.ic_human);
            bg.setBackground(human);
        }
        pincushion = Pincushion.getInstance();
        //getting & placing all pins from singleton
        for (int i = 0; i < pincushion.getSize(); i++){
            ImageView img = new ImageView(this);
            bg.addView(img);
            img.setImageDrawable(arrow);
            img.setX(pincushion.getAllPins().get(i).getX()-20);
            img.setY(pincushion.getAllPins().get(i).getY()-img.getDrawable().getIntrinsicHeight());
        }
        //getting coordinates from motion event
        touched = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {

                x = (int) e.getX();
                y = (int) e.getY();
                Log.i("LOG", "Tappage at: " + x + ", " + y);
                newTap();
                return false;
            }
        };

        bg.setOnTouchListener(touched);
    }

    //adding a new pin image at point of touch and starting confirmation activity
    public void newTap() {

        ImageView img = new ImageView(this);
        bg.addView(img);
        img.setImageDrawable(arrow);
        img.setX(x-20);
        img.setY(y-img.getDrawable().getIntrinsicHeight());
        Intent newPain = new Intent(ModelActivity.this, ConfirmNewActivity.class);
        newPain.putExtra("x", x);
        newPain.putExtra("y", y);
        startActivity(newPain);

    }

    //button to access history activity
    public void historyButtonPressed(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    //this bit was originally written by either Sebastian or Patrick
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
}