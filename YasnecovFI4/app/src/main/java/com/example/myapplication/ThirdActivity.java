package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button OkButton = findViewById(R.id.ok_button);

        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Получение ссылок на виджеты
                EditText dateText = findViewById(R.id.editTextDate);
                //Получение значений
                String date = dateText.getText().toString();
                Intent resultIntent =new Intent(ThirdActivity.this, SecondActivity.class);
                resultIntent.putExtra("date", date);
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

    }
}