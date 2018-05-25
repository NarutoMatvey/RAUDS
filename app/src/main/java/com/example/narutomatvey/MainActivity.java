package com.example.narutomatvey;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tNumBal, tIncAll, tCosAll, tSavAll;
    ImageButton btnIncomeAdd, btnCostsAdd, btnSavingAdd;
    Button btnIncomeAll, btnCostsAll, btnSavingAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tNumBal = (TextView) findViewById(R.id.textNumberBalance);
        tIncAll = (TextView) findViewById(R.id.textIncomeAll);
        tCosAll = (TextView) findViewById(R.id.textCostsAll);
        tSavAll = (TextView) findViewById(R.id.textSavingAll);

        btnIncomeAdd = (ImageButton) findViewById(R.id.buttonIncomeAdd);
        btnCostsAdd = (ImageButton) findViewById(R.id.buttonCostsAdd);
        btnSavingAdd = (ImageButton) findViewById(R.id.buttonSavingAdd);

        btnIncomeAll = (Button) findViewById(R.id.buttonIncomeAll);
        btnCostsAll = (Button) findViewById(R.id.buttonCostsAll);
        btnSavingAll = (Button) findViewById(R.id.buttonSavingAll);

        btnIncomeAdd.setOnClickListener((View.OnClickListener) this);
        btnCostsAdd.setOnClickListener((View.OnClickListener) this);
        btnSavingAdd.setOnClickListener((View.OnClickListener) this);

        btnIncomeAll.setOnClickListener((View.OnClickListener) this);
        btnCostsAll.setOnClickListener((View.OnClickListener) this);
        btnSavingAll.setOnClickListener((View.OnClickListener) this);
    }
}
