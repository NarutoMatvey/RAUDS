package com.example.narutomatvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoriesPageActivity extends AppCompatActivity {

    LinearLayout llFLCategory, llNLCategory, llLLCategory;
    EditText editAddCategory;
    ImageButton imBtnAddCategory;
    LinearLayout.LayoutParams lParams;
    String[] stringCateg = {"Категория 1", "Категория 2", "Категория 3",
                            "Категория 4", "Категория 5", "Категория 6",
                            "Категория 7", "Категория 8", "Категория 9"};
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);


        llFLCategory =  findViewById(R.id.firstLineCategory);
        llNLCategory =  findViewById(R.id.nextLineCategory);
        llLLCategory = findViewById(R.id.lastLineCategory);

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

        for(Integer i=0; i < stringCateg.length; i++) {
            TextView textCategory = new TextView(this);
            textCategory.setText(stringCateg[i]);
            if( 0 <= i % 9 && i % 9 <= 2 ){
                llFLCategory.addView(textCategory, lParams);
            }
            else if( 3 <= i % 9 && i % 9 <= 5 ){
                llNLCategory.addView(textCategory, lParams);
            }
            else if( 6 <= i % 9 && i % 9 <= 8 ){
                llLLCategory.addView(textCategory, lParams);
            }
        }
    }
}

