package com.example.yasnecovfi7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Запуск сервиса для вопроизведения музыки
        Intent startIntant = new Intent(this, MyService.class);
        startService(startIntant);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Остановка сервиса музыки после уничтоения активности
        Intent stopIntant = new Intent(this, MyService.class);
        stopService(stopIntant);
    }
}