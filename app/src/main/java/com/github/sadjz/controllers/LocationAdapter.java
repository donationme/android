package com.github.sadjz.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sadjz.R;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocViewHolder> {

    private String[] locDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class LocViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView locTextView;

        public LocViewHolder(TextView v) {
            super(v);
            locTextView = v;
        }
    }

    public LocationAdapter(String[] myDataset) {
        locDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LocationAdapter.LocViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loc_text_view, parent, false);
        LocViewHolder vh = new LocViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LocViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.locTextView.setText(locDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return locDataset.length;
    }
}