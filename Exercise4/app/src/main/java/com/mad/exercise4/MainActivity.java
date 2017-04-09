package com.mad.exercise4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.*;

/**
 * An application to display jokes sourced from a UTS website.
 *
 * Created by Jonathan Mann 11393269
 */
public class MainActivity extends AppCompatActivity {

    final static String urlBase = "http://www-staff.it.uts.edu.au/~rheise/sarcastic.cgi?len=";

    Context context;
    TextView jokeText;
    Spinner spinner;

    /**
     * The basic onCreate method.
     * Layout elements are assigned to variables.
     * Toast for spinner declared.
     *
     * @param savedInstanceState saved state bundle information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        jokeText = (TextView) findViewById(R.id.jokeText);

        spinner = (Spinner) findViewById(R.id.jokeLengthSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast toast = Toast.makeText(context, selectedItem + getString(R.string.length_selected_toast), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * onClick listener for layout buttons.
     * Call asynchronous methods to display jokes.
     *
     * @param view button view clicked
     */
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.joke1button:
                new Download1JokeAsyncTask().execute();
                break;
            case R.id.joke3button:
                new DownloadNJokesAsyncTask(3).execute();
                break;
        }
    }

    /**
     * Class to asynchronously download a requested number of jokes.
     */
    private class DownloadNJokesAsyncTask extends AsyncTask<Void, Integer, String[]> {

        int numJokes;
        ProgressDialog progressDialog;

        /**
         * Constructor for class
         *
         * @param numJokes number of jokes
         */
        public DownloadNJokesAsyncTask(int numJokes) {
            this.numJokes = numJokes;
        }

        /**
         * Called prior to executing background class.
         * Sets up progress dialog.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(getString(R.string.progress_bar_text) + " " + 1 + "...");
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(numJokes);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        /**
         * Background task to get jokes and publish progress
         *
         * @param params null
         * @return string array of jokes
         */
        @Override
        protected String[] doInBackground(Void... params) {
            String[] jokes = new String[numJokes];
            for (int i = 0; i < numJokes; i++) {
                publishProgress(i);
                jokes[i] = getJoke();
            }

            return jokes;
        }

        /**
         * Updates progress bar with current progress
         *
         * @param values current progress
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
            progressDialog.setMessage(getString(R.string.progress_bar_text) + " " + (values[0] + 1) + "...");
        }

        /**
         * Called after background task is complete.
         * Dismisses progress dialog
         * Sets text field to display jokes
         *
         * @param jokes array of jokes to display
         */
        @Override
        protected void onPostExecute(String[] jokes) {
            super.onPostExecute(jokes);
            progressDialog.dismiss();
            jokeText.setText(jokesToString(jokes));
        }

        /**
         * Opens a URL connection to receive a joke from a UTS website.
         *
         * @return string of a single joke
         */
        private String getJoke() {
            String joke;

            try {
                URL url = new URL(urlBase + spinner.getSelectedItem().toString());
                URLConnection conn = url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                joke = in.readLine();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                joke = getString(R.string.download_fail_text);
            }
            return joke;
        }

        /**
         * Convert an array of jokes into a single string to display
         *
         * @param jokes array of jokes
         * @return string of all jokes
         */
        private String jokesToString(String[] jokes) {
            String output = "";
            for (int i = 0; i < numJokes; i++) {
                output += jokes[i];
                if (i != numJokes - 1)
                    output += "\n\n";

                if (jokes[i].equals(getString(R.string.download_fail_text))) {
                    return getString(R.string.download_fail_text_plural);
                }
            }
            return output;
        }
    }

    /**
     * Class to asynchronously download a joke from a UTS website
     */
    private class Download1JokeAsyncTask extends AsyncTask<Void, Void, String> {

        ProgressDialog progressDialog;

        /**
         * Called prior to the background task.
         * Sets up and displays progress dialog.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(getString(R.string.progress_bar_text) + "...");
            progressDialog.show();
        }

        /**
         * Background task to get a joke
         *
         * @param params null
         * @return string of joke
         */
        @Override
        protected String doInBackground(Void... params) {
            return getJoke();
        }

        /**
         * Called after background task.
         * Displays joke and dismisses progress dialog
         *
         * @param joke joke to display
         */
        @Override
        protected void onPostExecute(String joke) {
            super.onPostExecute(joke);
            jokeText.setText(joke);
            progressDialog.dismiss();
        }

        /**
         * Opens a URL connection to receive a joke from a UTS website.
         * <p>
         * NOTE: this is identical to the method in DownloadNJokesAsyncTask.  Left this way for easier marking.
         *
         * @return string of a single joke
         */
        private String getJoke() {
            String joke;
            try {
                URL url = new URL(urlBase + spinner.getSelectedItem().toString());
                URLConnection conn = url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                joke = in.readLine();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                joke = getString(R.string.download_fail_text);
            }
            return joke;
        }
    }
}
