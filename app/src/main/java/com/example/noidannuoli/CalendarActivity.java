package com.example.noidannuoli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

/**
 * The Class activity that shows a single CalendarView
 * @author Sebastian Wolf
 * @version 14.12.2021
 */
public class CalendarActivity extends AppCompatActivity {

    private CalendarView pinCalendarView;
    int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        x = getIntent().getExtras().getInt("x");
        y = getIntent().getExtras().getInt("y");

        pinCalendarView = (CalendarView) findViewById(R.id.calendarViewPins);

        /* Listener to send the date in string format to MainActivity */
        pinCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;

                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                startActivity(intent);
            }
        });
    }
}