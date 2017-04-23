package com.mad.exercise5;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private List<Train> trainsList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView platform, status, destinationTime, arrivalTime, destination;

        public ViewHolder(View itemView) {
            super(itemView);
            platform = (TextView) itemView.findViewById(R.id.textview_platform);
            status = (TextView) itemView.findViewById(R.id.textview_status);
            destination = (TextView) itemView.findViewById(R.id.textview_destination);
            destinationTime = (TextView) itemView.findViewById(R.id.textview_destination_time);
            arrivalTime = (TextView) itemView.findViewById(R.id.textview_arrival_time);
            itemView.findViewById(R.id.train_time_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new UpdateArrivalTime(v).execute();
                }
            });
        }
    }

    public TrainAdapter(Context context, ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Train train = trainsList.get(position);
        holder.platform.setText(train.getPlatform());
        holder.status.setText(train.getStatus());
        holder.destination.setText(train.getDestination​());
        holder.destinationTime.setText(train.getDestinationTime​());
        holder.arrivalTime.setText(Integer.toString(train.getArrivalTime()) + "\n" + context.getString(R.string.arrival_time_min_suffix));
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }

    private class UpdateArrivalTime extends AsyncTask<Void,Void, Integer> {

        View view;
        ProgressBar progressBar;
        TextView arrivalTextView;

        public UpdateArrivalTime(View itemView) {
            view=itemView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (ProgressBar)view.findViewById(R.id.train_progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            arrivalTextView = (TextView)view.findViewById(R.id.textview_arrival_time);
            arrivalTextView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new Random().nextInt(20) + 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }


}
