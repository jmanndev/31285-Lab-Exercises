package com.mad.exercise4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.joke1button:
                new Download1JokeAsyncTask().execute();
                break;
            case R.id.joke3button:
                break;
        }
    }

    private class Download1JokeAsyncTask extends AsyncTask<Void, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Downloading jokes...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String joke = "";
            try {
                URL url = new URL("http://www-staff.it.uts.edu.au/~rheise/sarcastic.cgi");
                URLConnection conn = url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                joke = in.readLine();
                in.close();
            } catch (Exception e) {
                joke = "Failed to download joke";
            }
            return joke;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ((TextView)findViewById(R.id.jokeText)).setText(s);
            progressDialog.dismiss();
        }
    }
}
