package com.example.yasnecovfi12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    Button create_button,load_button, json_upload_button, json_download_button;
    TextView movieTxt, yearTxt;
    EditText movieEditTxt, yearEditTxt;
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.app.provider/my_movies");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_button =  findViewById(R.id.create_button);
        load_button = findViewById(R.id.load_button);
        json_download_button = findViewById(R.id.json_download_button);
        json_upload_button = findViewById(R.id.json_upload_button);
        movieTxt = findViewById(R.id.textViewMovie);
        yearTxt = findViewById(R.id.textViewYar);
        movieEditTxt = findViewById(R.id.editTextMovieTitle);
        yearEditTxt = findViewById(R.id.editTextMovieYear);

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
        json_upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = movieEditTxt.getText().toString();
                String year = yearEditTxt.getText().toString();
                Movie movie = new Movie(title,year);
                Gson gson = new Gson();
                String json = gson.toJson(movie);
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(getFilesDir()+"/jsonFile.json");
                    fileWriter.write(json);
                    fileWriter.close();
                    Toast.makeText(MainActivity.this, "Записано в " + getFilesDir()+"/jsonFile.json", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        json_download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(getFilesDir()+ "/jsonFile.json");
                    Gson gson = new Gson();
                    Movie movie = gson.fromJson(fileReader,Movie.class);

                    movieTxt.setText(movie.getMovie_title());
                    yearTxt.setText(movie.getMovie_year());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}