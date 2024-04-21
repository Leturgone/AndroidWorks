package com.example.yasnecovfi10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    EditText editMovieTitle, editDirector, editYear, editDescription, editLength;
    ImageView editImage;
    Button saveButton, updateButton, deleteButton,findButton;
    RecyclerView movieList;
    private final  int GALLERY_REQUEST_CODE = 1000;

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

        movieList = findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(BaseActivity.this);

        List<Movie> movies = myDB.getAllMovies();
        MovieAdapter adapter = new MovieAdapter(movies);
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(adapter);

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (requestCode == GALLERY_REQUEST_CODE){
                //Для галлереи
                editImage.setImageURI(data.getData());
            }
        }
    }
}