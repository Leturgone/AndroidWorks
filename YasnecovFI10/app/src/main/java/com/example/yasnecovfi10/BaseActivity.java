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
import android.widget.Toast;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    EditText editMovieTitle, editDirector, editYear, editDescription, editLength, editOldTitle, editOldYear;
    ImageView editImage;
    Button saveButton, updateButton, deleteButton,findButton;
    RecyclerView movieList;
    List<Movie> movies;
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

        movies = myDB.getAllMovies();
        MovieAdapter adapter = new MovieAdapter(movies);
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editMovieTitle.getText().toString();
                String director = editDirector.getText().toString();
                String year = editYear.getText().toString();
                String description = editDescription.getText().toString();
                String length = editLength.getText().toString();
                Movie movie = new Movie(0,title,director,year,description,editImage,length);
                if (myDB.addMovie(movie)){
                    movies.add(movie);
                    adapter.notifyItemInserted(movies.size() - 1);
                    refreshMoviesList(myDB,movies,adapter,movieList);
                    Toast.makeText(BaseActivity.this, "Фильм сохранен", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BaseActivity.this, "Ошибка при сохранении", Toast.LENGTH_SHORT).show();

                }
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOldTitle = findViewById(R.id.oldTitle);
                editOldYear = findViewById(R.id.oldYear);
                String old_title = editOldTitle.getText().toString();
                String old_year = editOldYear.getText().toString();
                String new_title = editMovieTitle.getText().toString();
                String new_director = editDirector.getText().toString();
                String new_year = editYear.getText().toString();
                String new_description = editDescription.getText().toString();
                String new_length = editLength.getText().toString();

                if(myDB.updateMovie(
                        old_title,old_year,new_title,new_director,new_year,new_description,editImage,new_length
                )){
                    Toast.makeText(BaseActivity.this, "Данные фильма обновлены", Toast.LENGTH_SHORT).show();
                    refreshMoviesList(myDB,movies,adapter,movieList);
                }
                else{
                    Toast.makeText(BaseActivity.this, "Ошибка при обновлении", Toast.LENGTH_SHORT).show();
                }


            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editMovieTitle.getText().toString();
                String year = editYear.getText().toString();
                movies = myDB.getAllMovies();
                if (myDB.deleteMovie(title,year)){
                    int position = -1;
                    for (int i = 0;i < movies.size(); i++){
                        if (movies.get(i).getMovie_title().equals(title)
                                && movies.get(i).getMovie_year().equals(year)){
                            position = i;
                            movies.remove(i);
                            break;
                        }
                    }
                    if (position != -1){
                        adapter.notifyItemRemoved(position);
                        refreshMoviesList(myDB,movies,adapter,movieList);
                        Toast.makeText(BaseActivity.this, "Фильм удален", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(BaseActivity.this, "Фильм не найден", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(BaseActivity.this, "Ошибка при удалении фильма", Toast.LENGTH_SHORT).show();
                }
            }

        });
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editMovieTitle.getText().toString();
                String year = editYear.getText().toString();
                Movie foundMovie = myDB.findMovie(title,year);
                if (foundMovie != null){
                    editDirector.setText(foundMovie.getMovie_director());
                    editDescription.setText(foundMovie.getMovie_description());
                    editLength.setText(foundMovie.getMovie_length());
                    editImage.setImageBitmap(foundMovie.getMovie_poster());
                    Toast.makeText(BaseActivity.this, "Фильм найден", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BaseActivity.this, "Фильм не найден", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    // Метод для обновления списка фильмов после изменения в базе данных
    private void refreshMoviesList(MyDatabaseHelper dbHelper,
                                     List<Movie> movies, MovieAdapter adapter, RecyclerView
                                             movieList) {
        movies = dbHelper.getAllMovies(); // Загружаем обновленный список;
        adapter = new MovieAdapter(movies);
        movieList.setAdapter(adapter);
    }

}
