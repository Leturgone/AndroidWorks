package com.example.yasnecovfi10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class BaseActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    EditText editMovieTitle, editDirector, editYear, editDescription, editLength;
    ImageView editImage;
    Button saveButton, updateButton, deleteButton,findButton;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        editMovieTitle = findViewById(R.id.editMovieTitle);
        editDirector = findViewById(R.id.editDirector);
        editYear = findViewById(R.id.editYear);
        editDescription = findViewById(R.id.editDescription);
        editLength = findViewById(R.id.editLength);
        editImage = findViewById(R.id.imgGallery);
        saveButton = findViewById(R.id.save_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        findButton = findViewById(R.id.find_button);

        myDB = new MyDatabaseHelper(BaseActivity.this);
        
    }
}