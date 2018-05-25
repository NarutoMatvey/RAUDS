package com.example.narutomatvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddingPageActivity extends AppCompatActivity {


    EditText edValDate, edValTime, edValAmount, edComment;
    TextView textCategories, textCurrency;
    ImageButton imBtnCategories, imBtnCurrency,imBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_page);

        edValDate = (EditText) findViewById(R.id.editValueDate);
        edValTime = (EditText) findViewById(R.id.editValueTime);
        edValAmount = (EditText) findViewById(R.id.editValueAmount);
        edComment = (EditText) findViewById(R.id.editComment);

        textCategories = (TextView) findViewById(R.id.textCategories);
        textCurrency = (TextView) findViewById(R.id.textCurrency);

        imBtnCategories = (ImageButton) findViewById(R.id.imBtnCategories);
        imBtnCurrency = (ImageButton) findViewById(R.id.imBtnCurrency);
        imBtnSubmit = (ImageButton) findViewById(R.id.imBtnSubmit);
    }
}
