package com.github.sadjz.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.sadjz.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type List adapter.
 */
@SuppressWarnings("CyclicClassDependency")
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<String> mData = new ArrayList<>();
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    /**
     * Instantiates a new List adapter.
     *
     * @param context the context
     */
// data is passed into the constructor
    public ListAdapter(Context context) {

        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * Update list.
     *
     * @param data the data
     */
    public void updateList(Collection<String> data) {
        mData.clear();
        mData.addAll(data);
        this.notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.loc_text_view, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = mData.get(position);
        holder.myTextView.setText(item);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * The type View holder.
     */
// stores and recycles views as they are scrolled off screen
    @SuppressWarnings("CyclicClassDependency")
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        /**
         * The My text view.
         */
        final TextView myTextView;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.locationName);
            final ViewHolder viewHolder = this;
            itemView.setOnClickListener(viewHolder);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    /**
     * Sets click listener.
     *
     * @param itemClickListener the item click listener
     */
// allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /**
     * The interface Item click listener.
     */
// parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        /**
         * On item click.
         *
         * @param position the position
         */
        void onItemClick(int position);
    }
}
