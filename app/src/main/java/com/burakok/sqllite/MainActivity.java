package com.burakok.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians" , MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR , age INT)"); // Tablo yoksa oluştur

            //database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR , age INT)"); //Usteki ile farkı Primary key Id ve Tablo yoksa oluştur
            //database.execSQL("UPDATE musicians SET age = 61 WHERE id=1"); //Guncelleme
            //database.execSQL("DELETE FROM musicians WHERE id = 1"); // Silme

            database.execSQL("INSERT INTO musicians (name , age) VALUES ('James',50)"); //Ornek Ekleme yapılıyor

            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null); // sorgu yapılıyor
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%' " , null); //ismi K harfi ile baslıyanlar sorgu

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getString(ageIx));
                System.out.println("Id: " + cursor.getString(idIx));
            }

            cursor.close();

        }catch (Exception exception){
                exception.printStackTrace();
        }
    }
}