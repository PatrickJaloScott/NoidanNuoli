package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class ConfirmNewActivity extends Activity {
    int x, y;
    Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_new);

        yes = findViewById(R.id.buttonYes);
        no = findViewById(R.id.buttonNo);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width, height);

        x = getIntent().getExtras().getInt("x");
        y = getIntent().getExtras().getInt("y");
    }

    public void backButton(View v){
        Intent goBack = new Intent(this, ModelActivity.class);
        startActivity(goBack);
    }
    public void confirmButton(View v){
        Intent confirmed = new Intent(this, MainActivity.class);
        confirmed.putExtra("x", x);
        confirmed.putExtra("y", y);
        startActivity(confirmed);
    }
}