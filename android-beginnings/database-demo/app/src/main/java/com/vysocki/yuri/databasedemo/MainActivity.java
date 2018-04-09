package com.vysocki.yuri.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Katya', 22)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Yuri', 23)");
            //sqLiteDatabase.execSQL("DELETE FROM users WHERE name = 'Yondu'");
            //sqLiteDatabase.execSQL("UPDATE users SET age = 21 WHERE name = 'Nikitka'");
            sqLiteDatabase.execSQL("DELETE from newUsers WHERE id = 1");
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while (c != null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                Log.i("id", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
