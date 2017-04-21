package com.mad.exercise5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //String platform, int arrivalTime, String status, String destination, String destinationTime

        ArrayList<Train> trains = new ArrayList<>();
        trains.add(new Train("Albion Park Platform 1", 3, "On time", "Allawah", "14:11"));
        trains.add(new Train("Arncliffe Platform 2", 4, "Late", "Central", "14:34"));
        trains.add(new Train("Artarmon Platform 3", 7, "On time", "Ashfield", "15:01"));
        trains.add(new Train("Berowra Platform 4", 12, "Late", "Beverly", "15:18"));

        TrainAdapter trainAdapter = new TrainAdapter(this, trains);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_delete_all:
                actionDeleteAll();
                return true;
            case R.id.action_quit:
                actionQuit();
                return true;
            case R.id.action_refresh:
                actionRefresh();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actionDeleteAll() {

    }

    private void actionQuit() {

    }

    private void actionRefresh() {

    }
}
