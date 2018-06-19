package com.example.narutomatvey;

import android.content.Intent;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddingPageActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    EditText edValDate, edValTime, edValAmount, edComment;
    TextView textCategories, textCurrency;
    ImageButton imBtnCategories, imBtnCurrency,imBtnSubmit;
    String labelTitle;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_page);
        labelTitle = getIntent().getStringExtra("labelTitle");
        setTitle(labelTitle);

        edValDate =  findViewById(R.id.editValueDate);
        edValTime =  findViewById(R.id.editValueTime);
        edValAmount = findViewById(R.id.editValueAmount);
        edComment = findViewById(R.id.editComment);

        textCategories = findViewById(R.id.textCategories);
        textCurrency = findViewById(R.id.textCurrency);

        imBtnCategories = findViewById(R.id.imBtnCategories);
        imBtnCurrency = findViewById(R.id.imBtnCurrency);
        imBtnSubmit = findViewById(R.id.imBtnSubmit);
    }

    public void openViewDateTime(View view) {
        switch (view.getId()) {
            case R.id.editValueDate:
                break;
            case R.id.editValueTime:
                break;
        }

    }

    public void openViewActivity(View view) {
        switch (view.getId()) {
            case R.id.imBtnCategories:
                intent = new Intent(this, CategoriesPageActivity.class);
                break;
            case R.id.imBtnCurrency:
                intent = new Intent(this, CategoriesPageActivity.class);
                break;
        }
        startActivity(intent);
    }

}
