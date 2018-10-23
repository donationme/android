package com.github.sadjz.controllers;


import android.support.v7.app.AppCompatActivity;

import com.github.sadjz.R;
import android.os.Bundle;
import android.widget.TextView;

public class NewItem extends AppCompatActivity {

    private TextView timeStampTextField;
    private TextView locationTextField;
    private TextView shortDescriptionTextField;
    private TextView longDescriptionTextField;
    private TextView valueTextField;
    private TextView categoryTextField;
    private TextView commentsTextField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);


    }

}
