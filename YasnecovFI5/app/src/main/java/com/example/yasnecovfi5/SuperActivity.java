package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SuperActivity extends AppCompatActivity {

    String[] superList = {"Новый Человек Паук","Новый Человек Паук 2", "Бэтмен","Доктор Стрендж", "Железный человек",
    "Джокер"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);

        ListView superListView = (ListView) findViewById(R.id.super_list);
        //создаем адаптер
        ArrayAdapter<String> superAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1,superList);
        //Установка адаптера

        superListView.setAdapter(superAdapter);

        //Функция при нажатии
        superListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //переход на активность:
                if (position == 0){
                    startActivity(new Intent(SuperActivity.this, RecActivity.class));
                }
                if (position == 1){
                    startActivity(new Intent(SuperActivity.this, ScrollActivity.class));
                }
            }
        });

    }
}