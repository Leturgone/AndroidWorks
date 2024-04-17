package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] genreList = {"Супергеройский Экшен","Мюзикл","Драма"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим список из xml
        ListView genreListView = (ListView) findViewById(R.id.genre_list);

        //создаем адаптер
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1,genreList);
        //Установка адаптера

        genreListView.setAdapter(genreAdapter);

        //Функция при нажатии
        genreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //переход на активность:
                 if (position == 0){
                     startActivity(new Intent(MainActivity.this, SuperActivity.class));
                 }
                 else if (position == 1){
                     startActivity(new Intent(MainActivity.this, MusicleActivity.class));
                 }
                 else if (position ==2){
                     startActivity(new Intent(MainActivity.this, DramaActivity.class));
                 }
            }
        });
    }
}