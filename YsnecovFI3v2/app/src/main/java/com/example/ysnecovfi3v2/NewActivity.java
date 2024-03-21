package com.example.ysnecovfi3v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        TextView message = new TextView(this);
        message.setTextSize(26);
        message.setPadding(16,16,16,16);
        MyObject objectInput = (MyObject) getIntent().getSerializableExtra("object");

        if(objectInput!=null){
            message.setText("Имя: " + objectInput.getName() + "\nВозраст: " + objectInput.getAge());
        }
        setContentView(message);

        Button BackButton = findViewById(R.id.back_button);

//        BackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NewActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


    }
}