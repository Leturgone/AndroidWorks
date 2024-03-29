package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MusicleActivity extends AppCompatActivity {

    String[] musicleList = {"Hello Dolly","Tick Tick Boom","La la land"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicle);

        ListView musicleListView = (ListView) findViewById(R.id.musicle_list);

        ArrayAdapter<String> musicleAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,musicleList);

        musicleListView.setAdapter(musicleAdapter);

        //Функция при нажатии
        musicleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //переход на активность:
                if (position == 0){
                    startActivity(new Intent(MusicleActivity.this, SpinActivity.class));
                }
            }
        });

    }
}