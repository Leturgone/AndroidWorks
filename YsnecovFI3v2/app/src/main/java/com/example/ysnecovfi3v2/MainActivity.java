package com.example.ysnecovfi3v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LinerButton = findViewById(R.id.LinerLayout_button);
        Button FrameButton = findViewById(R.id.FrameLayout_button);
        Button RelativeButton = findViewById(R.id.RelativeLayout_button);
        Button NewActivityButton = findViewById(R.id.NewActivity_button);

        LinerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Нажатие на кнопку
                Intent intent = new Intent(MainActivity.this, LinerActivity.class);
                startActivity(intent);
            }
        });

        FrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FrameActivity.class);
                startActivity(intent);
            }
        });

        RelativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelativeActivity.class);
                startActivity(intent);
            }
        });

        NewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                MyObject object = new MyObject("Mark",26);
                intent.putExtra("object",object);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }
}