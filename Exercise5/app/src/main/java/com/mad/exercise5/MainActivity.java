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
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main activity of a project to update/refresh Train arrival times.
 * Created by Jonathan Mann 11393269
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Train> trainsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainAdapter trainAdapter;

    /**
     * OnCreate method to assign click listeners, prepare recycler view, and prepare demo train data
     *
     * @param savedInstanceState Bundle that can be restored (not used for this project)
     */
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
        trainAdapter = new TrainAdapter(this, trainsList);

        RecyclerView.LayoutManager trainLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(trainLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trainAdapter);

        prepareTrainData();
    }

    /**
     * Add sample train data to the train list.  Notify adapter of changes.
     *
     * The train status message is stored in strings.xml but all the others are hardcoded as they are placeholder data.
     */
    private void prepareTrainData() {
        //String platform, int arrivalTime, String status, String destination, String destinationTime

        trainsList.add(new Train("Albion Park Platform 1", 3, getString(R.string.train_status_ontime), "Allawah", "14:11"));
        trainsList.add(new Train("Arncliffe Platform 2", 4, getString(R.string.train_status_late), "Central", "14:34"));
        trainsList.add(new Train("Artarmon Platform 3", 7, getString(R.string.train_status_ontime), "Ashfield", "15:01"));
        addBerowraTrainDataToList();
        trainAdapter.notifyDataSetChanged();
    }

    /**
     * Add Berowra train data to list.  This is a separate function as the FAB adds copies of this train.
     */
    private void addBerowraTrainDataToList() {
        trainsList.add(new Train("Berowra Platform 4", 12, getString(R.string.train_status_late), "Beverly", "15:18"));
    }

    /**
     * Creates the options menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Called when an options item is selected.
     *
     * @param item The item selected
     * @return
     */
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

    /**
     * Delete all train entries from list
     */
    private void actionDeleteAll() {
        trainsList.clear();
        trainAdapter.notifyDataSetChanged();
    }

    /**
     * Empty placeholder class.  Could implement function to continue through the lifecycle to close app.
     */
    private void actionQuit() {

    }

    /**
     * Calls AsyncTask to refresh all train arrival times
     */
    private void actionRefresh() {
        new UpdateArrivalTimes().execute();
    }

    /**
     * Async class to generate random arrival times for all trains.
     */
    private class UpdateArrivalTimes extends AsyncTask<Void, Void, String> {
        ProgressBar progressBar;

        /**
         * PreExecute: hide recycler view and show progress bar
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setVisibility(View.INVISIBLE);
        }

        /**
         * Sleep for 3 seconds and generate random data.
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //I don't believe there is an appropriate way to handle this exception as the code will continue regardless if the timer fails.
            }

            Random rand = new Random();

            for (int i = 0; i < trainsList.size(); i++) {
                trainsList.get(i).setArrivalTime(rand.nextInt(20) + 1);
            }
            return null;
        }

        /**
         * OnPostExecute: show recycler view and hide progress bar
         *
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            trainAdapter.notifyDataSetChanged();
        }
    }

}
