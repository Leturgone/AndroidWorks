package com.example.yasnecovfi7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
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

        //Создание обработчика выбора даты для DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Обработка даты
                Fragment tempFragment = new DatePickerFragment();
                //Передача данных фрагменту
                Bundle args = new Bundle();
                args.putInt("year",year);
                args.putInt("month",month);
                args.putInt("day",dayOfMonth);
                tempFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.alert_fragment,tempFragment).commit();
            }
        };
        //Создание DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, dateSetListener, year, month,day);



        //Создание кастомного окна

        //Создание диалога
        Dialog dialog = new Dialog(MainActivity.this);
        //Установка макета для диалогоовго окна
        dialog.setContentView(R.layout.custom_dialog);
        //Настройка элементов в макете
        Button button = dialog.findViewById(R.id.button);
        EditText editText = dialog.findViewById(R.id.editTextText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Fragment tempFragment = new CustomDialogFragment();
                Bundle args = new Bundle();
                args.putString("input",text);
                tempFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.alert_fragment,tempFragment).commit();
                dialog.dismiss();


            }
        });
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
                datePickerDialog.show();
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
                dialog.show();

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