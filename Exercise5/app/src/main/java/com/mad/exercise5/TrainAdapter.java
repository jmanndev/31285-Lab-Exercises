package com.mad.exercise5;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Train adapter class for adapter/recyclerView implementation
 */
public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private List<Train> trainsList;
    private Context context;
    public TextView platform, status, destinationTime, arrivalTime, destination;

    /**
     * Class for a single row of data in adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.train_time_layout).setOnClickListener(this);
            platform = (TextView) itemView.findViewById(R.id.textview_platform);
            status = (TextView) itemView.findViewById(R.id.textview_status);
            destination = (TextView) itemView.findViewById(R.id.textview_destination);
            destinationTime = (TextView) itemView.findViewById(R.id.textview_destination_time);
            arrivalTime = (TextView) itemView.findViewById(R.id.textview_arrival_time);
        }

        /**
         * OnClick for updating arrival time
         *
         * @param v View
         */
        @Override
        public void onClick(View v) {
            new UpdateArrivalTime(v, getAdapterPosition()).execute();
        }
    }

    /**
     * Constructor to assign parameters to class variables
     *
     * @param context
     * @param trainsList
     */
    public TrainAdapter(Context context, ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
        this.context = context;
    }

    /**
     * Inflates content to display in view holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Binds content in list to view holder row
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Train train = trainsList.get(position);
        platform.setText(train.getPlatform());
        status.setText(train.getStatus());
        destination.setText(train.getDestination​());
        destinationTime.setText(train.getDestinationTime​());
        arrivalTime.setText(Integer.toString(train.getArrivalTime()) + "\n" + context.getString(R.string.arrival_time_min_suffix));

        if (train.getStatus().equals(context.getString(R.string.train_status_late))) {
            status.setTextColor(ContextCompat.getColor(context, R.color.colorLateText));
        }
    }

    /**
     * Gets the item count of the list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return trainsList.size();
    }

    /**
     * Async Task to refresh a single arrival time
     */
    private class UpdateArrivalTime extends AsyncTask<Void, Void, Integer> {

        View view;
        ProgressBar progressBar;
        TextView arrivalTextView;
        int position;

        /**
         * Constructor to assign parameters to local variables
         *
         * @param itemView
         * @param position
         */
        public UpdateArrivalTime(View itemView, int position) {
            view = itemView;
            this.position = position;
        }

        /**
         * Pre execute to hide arrival text and show progress bar
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar) view.findViewById(R.id.train_progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            arrivalTextView = (TextView) view.findViewById(R.id.textview_arrival_time);
            arrivalTextView.setVisibility(View.INVISIBLE);
        }

        /**
         * Sleep 2 seconds and generate random number for arrival time
         *
         * @param params
         * @return random arrival time
         */
        @Override
        protected Integer doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //I don't believe there is an appropriate way to handle this exception as the code will continue regardless if the timer fails.
            }

            return new Random().nextInt(20) + 1;
        }

        /**
         * Assign paramater to train list, update view and hide progress bar.
         *
         * @param arrivalTime random arrival time
         */
        @Override
        protected void onPostExecute(Integer arrivalTime) {
            super.onPostExecute(arrivalTime);
            trainsList.get(position).setArrivalTime(arrivalTime);
            progressBar.setVisibility(View.GONE);
            arrivalTextView.setVisibility(View.VISIBLE);
            notifyDataSetChanged();
        }
    }


}
