package com.example.narutomatvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoriesPageActivity extends AppCompatActivity {

    LinearLayout llCategory;
    EditText editAddCategory;
    ImageButton imBtnAddCategory;
    LinearLayout.LayoutParams lParams;
    String mySt = "Категория 1";
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        llCategory =  findViewById(R.id.llCategory);
        editAddCategory = findViewById(R.id.editAddCategory);
        imBtnAddCategory = findViewById(R.id.imBtnAddCategory);
        Log.d(LOG_TAG, "Нашёл компоненты");
        Log.d(LOG_TAG, "---------------------------------");
        loadingDataCategory();

    }

    private void loadingDataCategory() {
        Log.d(LOG_TAG, "Функция создания View");
        Log.d(LOG_TAG, "---------------------------------");

        lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textCategory = new TextView(this);
        textCategory.setText(mySt);
        llCategory.addView(textCategory, lParams);
    }
}

