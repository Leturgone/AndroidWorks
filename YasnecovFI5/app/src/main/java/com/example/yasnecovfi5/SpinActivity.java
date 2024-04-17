package com.example.yasnecovfi5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin);

        //Находим элемент Spinner
        Spinner spinner_name = findViewById(R.id.spinner);

        String[] actors = new String[] {"Rayan Gosling", "Emma Stone", "John Legend", "Rosemarie DeWitt", "J.K. Simmons"};

        //Создаем адапотер с ресурса строк и стандартной разметки элемента spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,actors);
        //Применяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Применяем адаптер к элементу spinner
        spinner_name.setAdapter(adapter);
    }
}