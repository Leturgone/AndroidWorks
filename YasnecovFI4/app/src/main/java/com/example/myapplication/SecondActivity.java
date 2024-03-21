package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button VVButton = findViewById(R.id.enter_button);

        VVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
}