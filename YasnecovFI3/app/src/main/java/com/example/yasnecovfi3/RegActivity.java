package com.example.yasnecovfi3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        TextView messageText = new TextView(this);
        messageText.setTextSize(26);
        messageText.setPadding(16,16,16,16);
        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            String name = arguments.get("name").toString();
            String group = arguments.get("group").toString();
            int age = arguments.getInt("age");
            int mark = arguments.getInt("mark");
            messageText.setText("ФИО: " + name +"\nГруппа: " + group + "\nВозраст: " + age + "\nОценка: " + mark);
        }
        setContentView(messageText);
    }
}