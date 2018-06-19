package com.example.narutomatvey;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs", DB_NAME = "myAccounting";

    TextView tNumBal, tIncAll, tCosAll, tSavAll;
    Intent intent;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

//    Данные для таблиц
    String[] name_category = {"Продукты", "Гульки", "Проезд", "Зарплата", "Степендия", "Помощь", "Машина", "Квартира", "Лампа"},
        type_category = {"Расходы", "Расходы", "Расходы", "Доходы", "Доходы", "Доходы", "Сбережения", "Сбережения", "Сбережения"},
        name_currency = {"Рубль", "Долар", "Евро"},
        abb_currency = {"RUB", "USD", "EUR"};
    Double[] currency_coefficient = {1.0, 63.1664, 75.122},
            flowofmany_summ = {177.25, 1008.3, 501.5},
            amount_goal = {1250000.0, 15000000.0, 2000.0};
    String[] flowofmany_type = {"Расходы", "Сбережения", "Доходы"},
            flowofmany_commen = {"Мясо", "", "Мама"},
            flowofmany_datetimet = {"2018-05-11 12:48:55.125",
                    "2018-06-13 01:44:55.255",
                    "2018-06-13 15:15:15.122"};
    Integer[] category_id = {2, 8, 6},
            currency_id = {1, 1, 1};




//    Менюшка
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(LOG_TAG, "Создал Меню");
        Log.d(LOG_TAG, "---------------------------------");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_settings:
                tNumBal.setText("Вы выбрали кота!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Создал Активити");
        Log.d(LOG_TAG, "---------------------------------");

        tNumBal = findViewById(R.id.textNumberBalance);
        tIncAll = findViewById(R.id.textIncomeAll);
        tCosAll = findViewById(R.id.textCostsAll);
        tSavAll = findViewById(R.id.textSavingAll);
        Log.d(LOG_TAG, "Инициализация полей текста");
        Log.d(LOG_TAG, "---------------------------------");

        dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor;

        Log.d(LOG_TAG, "---Table categorytable---");
        cursor = sqLiteDatabase.query("categorytable", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");


        Log.d(LOG_TAG, "---Table currencytable---");
        cursor = sqLiteDatabase.query("currencytable", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        Log.d(LOG_TAG, "---Table flowofmanytable---");
        cursor = sqLiteDatabase.query("flowofmanytable", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");


        Log.d(LOG_TAG, "---Table savingtable---");
        cursor = sqLiteDatabase.query("savingtable", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");
        setInitialValues(cursor);
    }


//    Метод для отображения потом удалю ---------------------------------------------------------
void logCursor(Cursor cursor) {
    if (cursor != null) {
        if (cursor.moveToFirst()) {
            String str;
            do {
                str = "";
                for (String cn : cursor.getColumnNames()) {
                    str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                }
                Log.d(LOG_TAG, str);
            } while (cursor.moveToNext());
        }
    } else Log.d(LOG_TAG, "Cursor is null");
}
//---------------------------------------------------------------------------------------------


//    Нажатие кнопок
    public void openAddDataPage(View view) {
        switch (view.getId()) {
            case R.id.buttonIncomeAdd:

                intent = new Intent(this, AddingPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.income));
                //"Нажата кнопка Доходы"
                break;
            case R.id.buttonCostsAdd:
                // "Нажата кнопка Расходы"
                intent = new Intent(this, AddingPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.costs));
                break;
            case R.id.buttonSavingAdd:
                //"Нажата кнопка Сбережения"
                intent = new Intent(this, AddingPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.saving));
                break;
        }
        startActivity(intent);
    }

    public void openStatisticPage(View view) {
        switch (view.getId()) {
            case R.id.buttonIncomeAll:
                intent = new Intent(this, StatisticPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.all_incomes));
                //"Нажата кнопка Доходы"
                break;
            case R.id.buttonCostsAll:
                // "Нажата кнопка Расходы"
                intent = new Intent(this, StatisticPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.all_costs));
                break;
            case R.id.buttonSavingAll:
                //"Нажата кнопка Сбережения"
                intent = new Intent(this, StatisticPageActivity.class);
                intent.putExtra("labelTitle", getString(R.string.all_saving));
                break;
        }
        startActivity(intent);
    }

//    Установка данных
    private void setInitialValues(Cursor cursor){
        Log.d(LOG_TAG, "Установка начальных значений");
        Log.d(LOG_TAG, "---------------------------------");
        cursor = sqLiteDatabase.query("informationtable", null, null,
                null, null, null, null);
        String data;
        cursor.moveToFirst();
        data = cursor.getString(cursor.getColumnIndex("balance"));
        if(data.equals("0")){
            tNumBal.setText(R.string.zero);
        }
        else {
            tNumBal.setText(data);
        }

        data = cursor.getString(cursor.getColumnIndex("income"));
        if(data.equals("0")){
            tIncAll.setText(R.string.zero);
        }
        else {
            tIncAll.setText(data);
        }

        data = cursor.getString(cursor.getColumnIndex("costs"));
        if(data.equals("0")){
            tCosAll.setText(R.string.zero);
        }
        else {
            tCosAll.setText(data);
        }

        data = cursor.getString(cursor.getColumnIndex("saving"));
        if(data.equals("0")){
            tSavAll.setText(R.string.zero);
        }
        else {
            tSavAll.setText(data);
        }
    }

//    Работа с базами данных
    class DBHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 1;

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, DB_NAME, null, DATABASE_VERSION);
            Log.d(LOG_TAG, "Создал Базы данных");
            Log.d(LOG_TAG, "---------------------------------");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            ContentValues contentValues = new ContentValues();

            db.execSQL("create table informationtable ("
                    + "id integer primary key,"
                    + "stendart_currency text not null,"
                    + "income real not null,"
                    + "costs real not null,"
                    + "saving real not null,"
                    + "balance real not null,"
                    + "income_category text not null,"
                    + "costs_category text not null,"
                    + "save_category text not null,"
                    + "password text" + ");");
            contentValues.put("id", 1);
            contentValues.put("stendart_currency", "RUB");
            contentValues.put("income", 0.0);
            contentValues.put("saving", 0.0);
            contentValues.put("costs", 0.0);
            contentValues.put("balance", 0.0);
            contentValues.put("income_category", "Продукты");
            contentValues.put("costs_category", "Зарплата");
            contentValues.put("save_category", "Машина" );
            db.insert("informationtable", null, contentValues);
            Log.d(LOG_TAG, "Создал Таблицу Информации");
            Log.d(LOG_TAG, "---------------------------------");

            // создаем таблицу с полями
//            Таблица Котегорий
            db.execSQL("create table categorytable ("
                    + "id integer primary key autoincrement,"
                    + "name text not null,"
                    + "type text not null" + ");");
            Log.d(LOG_TAG, "Создал Таблицу Котегорий");
            Log.d(LOG_TAG, "---------------------------------");
            for (int i = 0; i < name_category.length; i++) {
                contentValues.clear();
                contentValues.put("name", name_category[i]);
                contentValues.put("type", type_category[i]);
                db.insert("categorytable", null, contentValues);
            }
            Log.d(LOG_TAG, "Добавил данные 1");
            Log.d(LOG_TAG, "---------------------------------");


//            Таблица Валют
            db.execSQL("create table currencytable ("
                    + "id integer primary key autoincrement,"
                    + "name text not null,"
                    + "name_abb text not null,"
                    + "coefficient real not null" + ");");
            Log.d(LOG_TAG, "Создал Таблицу Валют");
            Log.d(LOG_TAG, "---------------------------------");
            for (int i = 0; i < name_currency.length; i++) {
                contentValues.clear();
                contentValues.put("name", name_currency[i]);
                contentValues.put("name_abb", abb_currency[i]);
                contentValues.put("coefficient", currency_coefficient[i]);
                db.insert("currencytable", null, contentValues);
            }
            Log.d(LOG_TAG, "Добавил данные 2");
            Log.d(LOG_TAG, "---------------------------------");


//            Таблица Доходы_Расходы
            db.execSQL("create table flowofmanytable ("
                    + "id integer primary key autoincrement,"
                    + "type text not null,"
                    + "datetime_flowofmany text not null,"
                    + "category_id integer not null,"
                    + "summ_flowofmany real not null,"
                    + "currency_id integer not null,"
                    + "comment text,"
                    + "foreign key(category_id) references categorytable(id),"
                    + "foreign key(currency_id) references currencytable(id)" + ");");
            Log.d(LOG_TAG, "Создал Таблицу Доходы_Расходы");
            Log.d(LOG_TAG, "---------------------------------");
            for (int i = 0; i < flowofmany_type.length; i++) {
                contentValues.clear();
                contentValues.put("type", flowofmany_type[i]);
                contentValues.put("datetime_flowofmany", flowofmany_datetimet[i]);
                contentValues.put("category_id", category_id[i]);
                contentValues.put("summ_flowofmany", flowofmany_summ[i]);
                contentValues.put("currency_id", currency_id[i]);
                contentValues.put("comment", flowofmany_commen[i]);
                db.insert("flowofmanytable", null, contentValues);
            }
            Log.d(LOG_TAG, "Добавил данные 3");
            Log.d(LOG_TAG, "---------------------------------");


//            Таблица Сбережений
            db.execSQL("create table savingtable ("
                    + "id integer primary key autoincrement,"
                    + "category_id integer not null,"
                    + "amount_goal real not null,"
                    + "amount_collected real default 0.0,"
                    + "currency_id integer not null,"
                    + "foreign key(category_id) references categorytable(id),"
                    + "foreign key(currency_id) references currencytable(id)" + ");");
            Log.d(LOG_TAG, "Создал Таблицу Сбережений");
            Log.d(LOG_TAG, "---------------------------------");
            for (int i = 0; i < category_id.length; i++) {
                contentValues.clear();
                contentValues.put("category_id", category_id[i]);
                contentValues.put("amount_goal", amount_goal[i]);
                contentValues.put("currency_id", currency_id[i]);
                db.insert("savingtable", null, contentValues);
            }
            Log.d(LOG_TAG, "Добавил данные 4");
            Log.d(LOG_TAG, "---------------------------------");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL(DB_NAME);
//            // Создаём новый экземпляр таблицы
//            onCreate(db);

        }
    }
}