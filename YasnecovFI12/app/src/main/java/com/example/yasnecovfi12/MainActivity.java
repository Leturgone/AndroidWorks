package com.example.yasnecovfi12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.app.provider/my_movies");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create_button =  findViewById(R.id.create_button);
        Button load_button = findViewById(R.id.load_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new MyDatabaseHelper(MainActivity.this);
                Toast.makeText(MainActivity.this, "Бд создана", Toast.LENGTH_SHORT).show();
            }
        });
        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri contentUri =
                        Uri.parse("content://com.example.app.provider/my_movies");
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(contentUri, new String[] {"_id",
                        "movie_title", "movie_year"}, null, null, null);
                if (cursor != null) {
                    try {
                        int idColumn = cursor.getColumnIndex("_id");
                        int titleColumn = cursor.getColumnIndex("movie_title");
                        int yearColumn = cursor.getColumnIndex("movie_year");
                        while (cursor.moveToNext()) {
                            int id = cursor.getInt(idColumn);
                            String title = cursor.getString(titleColumn);
                            String year = cursor.getString(yearColumn);
                            Toast.makeText(MainActivity.this,title+ " " + year, Toast.LENGTH_SHORT).show();
                            System.out.println("Movie ID: " + id);
                            System.out.println("Movie Title: " + title);
                            System.out.println("Year: " + year);
                        }
                    } finally {
                        cursor.close(); // Важно закрыть курсор после использования
                    }
                }
            }
        });

    }
}