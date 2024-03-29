package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MusicleActivity extends AppCompatActivity {

    String[] musicleList = {"Hello Dolly","Tick Tick Boom","La la land"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicle);

        ListView musicleListView = (ListView) findViewById(R.id.musicle_list);

        ArrayAdapter<String> musicleAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_multiple_choice,musicleList);

        musicleListView.setAdapter(musicleAdapter);

        TextView selectedName = (TextView) findViewById(R.id.choose_names);
        //Функция при нажатии
        musicleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray selected = musicleListView.getCheckedItemPositions();

                String selectedItems = "";

                for(int i = 0; i<musicleList.length;i++){
                    if(selected.get(i)){
                        selectedItems+=musicleList[i]+",";
                    }
                }
                selectedName.setText("Выбрано: " +selectedItems);
            }
        });

    }
}