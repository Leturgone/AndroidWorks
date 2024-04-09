package com.example.yasnecovfi7;

import androidx.appcompat.app.AlertDialog;
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

        //Диалоговые окна

        //Создание строителя диалоговых окон
        AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);

        //Установка сообщения диалогового окна
        builder.setTitle("Подтверждение");
        builder.setMessage("Вы увидели это окно?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //Установка кнопки Да и ее обработчика


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Остановка сервиса музыки после уничтоения активности
        Intent stopIntant = new Intent(this, MyService.class);
        stopService(stopIntant);
    }
}