package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Получаем данные
        TextView message_name = (TextView) findViewById(R.id.text_name_info);
        TextView message_surname = (TextView) findViewById(R.id.text_surname_info);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
           String name = arguments.get("name").toString();
           String surname = arguments.get("surname").toString();
           message_name.setText("Имя: " + name);
           message_surname.setText("Фамилия: " + surname);
        }

    }
}