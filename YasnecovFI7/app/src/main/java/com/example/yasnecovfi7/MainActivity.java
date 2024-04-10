package com.example.yasnecovfi7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private int hour;
    private int minute;
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
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Fragment tempFragment  = new AlertDialogFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.alert_fragment, tempFragment).commit();
            }
        });

        //Установка кнопки Отмена и ее обработчика
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //Создание и отображение TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                //Обработка выбранного времени
                Fragment tempFragment  = new TimePickerFragment();
                //Передача данных фргаменту
                Bundle args = new Bundle();
                args.putInt("h",hour);
                args.putInt("m",minute);
                tempFragment.setArguments(args);

                getSupportFragmentManager().beginTransaction().replace(R.id.alert_fragment, tempFragment).commit();
            }
        },hour, minute,true);




        //Кнопки для появления окон

        Button alertButton = findViewById(R.id.alert_button);
        Button date_pickerButton = findViewById(R.id.date_picker_button);
        Button time_pickrButton = findViewById(R.id.time_picker_button);
        Button custom_dialofButton = findViewById(R.id.custom_dialog_button);

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Создание и отбражение AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        date_pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        time_pickrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        custom_dialofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Остановка сервиса музыки после уничтоения активности
        Intent stopIntant = new Intent(this, MyService.class);
        stopService(stopIntant);
    }
}