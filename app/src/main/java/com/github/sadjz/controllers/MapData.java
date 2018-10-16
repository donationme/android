package com.github.sadjz.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.github.sadjz.R;

public class MapData extends AppCompatActivity {

    private RecyclerView locRecyclerView;
    private RecyclerView.Adapter locAdapter;
    private RecyclerView.LayoutManager locLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_data);
        locRecyclerView = (RecyclerView) findViewById(R.id.locRecView);
        locRecyclerView.setHasFixedSize(true);

        locLayoutManager = new LinearLayoutManager(this);
        locRecyclerView.setLayoutManager(locLayoutManager);

        //locAdapter = new LocationAdapter(locationList); #server call
        //locRecyclerView.setAdapter(locAdapter);
    }
}
