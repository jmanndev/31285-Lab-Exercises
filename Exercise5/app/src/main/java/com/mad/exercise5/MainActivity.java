package com.mad.exercise5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Train> trainsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainAdapter trainAdapter;


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
                addBerowraTrainDataToList();
                trainAdapter.notifyDataSetChanged();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        trainAdapter = new TrainAdapter(this,trainsList);

        RecyclerView.LayoutManager trainLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(trainLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trainAdapter);


        prepareTrainData();

    }

    private void prepareTrainData() {
        //String platform, int arrivalTime, String status, String destination, String destinationTime
        Train train = new Train("Albion Park Platform 1", 3, "On time", "Allawah", "14:11");
        trainsList.add(train);

        trainsList.add(new Train("Arncliffe Platform 2", 4, "Late", "Central", "14:34"));
        trainsList.add(new Train("Artarmon Platform 3", 7, "On time", "Ashfield", "15:01"));
        addBerowraTrainDataToList();
        trainAdapter.notifyDataSetChanged();
    }

    private void addBerowraTrainDataToList() {
        trainsList.add(new Train("Berowra Platform 4", 12, "Late", "Beverly", "15:18"));
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
        trainsList.clear();
        trainAdapter.notifyDataSetChanged();
    }

    private void actionQuit() {

    }

    private void actionRefresh() {
        new UpdateArrivalTimes().execute();
    }

    private class UpdateArrivalTimes extends AsyncTask<Void, Void, String> {
        ProgressBar progressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Random rand = new Random();

            for (int i = 0; i < trainsList.size(); i++) {
                trainsList.get(i).setArrivalTime(rand.nextInt(20) + 1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            trainAdapter.notifyDataSetChanged();
        }
    }
}
