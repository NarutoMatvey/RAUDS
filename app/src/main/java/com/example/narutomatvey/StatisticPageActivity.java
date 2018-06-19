package com.example.narutomatvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticPageActivity extends AppCompatActivity {

    String labelTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_page);

        labelTitle = getIntent().getStringExtra("labelTitle");
        setTitle(labelTitle);

    }
}
