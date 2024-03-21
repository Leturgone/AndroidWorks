package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button OkButton = findViewById(R.id.ok_button);

        //Получение ссылок на виджеты
        EditText dateText = findViewById(R.id.editTextDate);
        //Получение значений
        String date = dateText.getText().toString();
    }
}