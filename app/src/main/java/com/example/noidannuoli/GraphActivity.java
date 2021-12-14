package com.example.noidannuoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    ArrayList<Pin> pinData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        pinData = Pincushion.getInstance().getAllPins();

        GraphView graph = (GraphView) findViewById(R.id.pinGraph);
        series = new LineGraphSeries<DataPoint>();
        int pinCount = pinData.size();
        double x = 0,y = 0;
        for (int i = 0; i<pinCount; i++) {
            x += 0.1;
            y = Double.parseDouble(pinData.get(i).intensity);
            series.appendData(new DataPoint(x, y), true, pinCount);
        }
        graph.addSeries(series);
    }
    

}