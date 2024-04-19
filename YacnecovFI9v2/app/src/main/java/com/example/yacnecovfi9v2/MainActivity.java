package com.example.yacnecovfi9v2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1;

    EditText FileName;
    Button readButton;
    TextView fileInf;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Запрос разрешений на чтение файлов

        FileName = findViewById(R.id.editFileNameText);
        readButton = findViewById(R.id.read_button);
        fileInf = findViewById(R.id.textView);
        Context context = MainActivity.this;
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.isExternalStorageManager()) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                    }
                    Toast.makeText(MainActivity.this,"Доступ разрешен",Toast.LENGTH_SHORT).show();
                    readFile();
                } else {
                    // Запрос разрешения
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });


    }

    private void readFile() {
        filename = FileName.getText().toString() + ".txt";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }


            String fileContent = stringBuilder.toString();
            fileInf.setText(fileContent);

            fileInputStream.close();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"Не удалось прочитать файл",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохраняем значение строковой переменной
        String textViewText = fileInf.getText().toString();
        outState.putString("KEY_STATE", textViewText);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Восстанавливаем сохраненное состояние
        String state = savedInstanceState.getString("KEY_STATE");

        fileInf.setText(state);

    }
}