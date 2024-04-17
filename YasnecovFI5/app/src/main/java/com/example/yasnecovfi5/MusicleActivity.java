package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MusicleActivity extends AppCompatActivity {

    ArrayList<String> movies = new ArrayList<String>();

    ArrayList<String> selectedMusicles = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicle);

        ArrayAdapter<String> musicleAdapter;
        ListView musicleListView;
        String[] musicleList = {"Hello Dolly","Tick Tick Boom","La la land"};

        Collections.addAll(movies, musicleList);
        musicleListView = (ListView) findViewById(R.id.musicle_list);

        musicleAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_multiple_choice,movies);

        musicleListView.setAdapter(musicleAdapter);

        Button plus = findViewById(R.id.plus_button);
        Button minus = findViewById(R.id.minus_button);


        //Функция при нажатии
        musicleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String musicle = musicleAdapter.getItem(position);
                if (musicleListView.isItemChecked(position)){
                    selectedMusicles.add(musicle);
                }
                else{
                    selectedMusicles.remove(musicle);

                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = findViewById(R.id.text_name);
                String user = userName.getText().toString();
                if(!user.isEmpty()){
                    musicleAdapter.add(user);
                    userName.setText("");
                    musicleAdapter.notifyDataSetChanged();
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получаем и удаляем выделенные элементы
                for(int i=0; i< selectedMusicles.size();i++){
                    musicleAdapter.remove(selectedMusicles.get(i));
                }
                // снимаем все ранее установленные отметки
                musicleListView.clearChoices();
                // очищаем массив выбраных объектов
                selectedMusicles.clear();
                musicleAdapter.notifyDataSetChanged();
            }
        });

    }



}
