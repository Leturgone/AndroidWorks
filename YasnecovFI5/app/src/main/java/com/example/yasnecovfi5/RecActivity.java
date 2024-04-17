package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class RecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);
        //Находим элемент RecycleView
        RecyclerView recyclerView = findViewById(R.id.list);
        //Устанавливаем макет отображения
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Список элементов
        List<String> items = Arrays.asList("Постер 1", "Постер 2", "Постер 3");
        List<Integer> images = Arrays.asList(R.drawable.poster1,R.drawable.poster2,R.drawable.poster3);
        //Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(items,images);
        //Устанавливаем для спсика адаптер
        recyclerView.setAdapter(adapter);


    }
}