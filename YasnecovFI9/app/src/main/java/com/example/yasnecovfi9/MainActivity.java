package com.example.yasnecovfi9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText FileName, FileStore;
    Button saveButton, readButton, deleteButton, addButton;
    TextView fileInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileName = findViewById(R.id.editFileNameText);
        FileStore = findViewById(R.id.editFileText);

        saveButton = findViewById(R.id.save_button);
        readButton = findViewById(R.id.read_button);
        deleteButton = findViewById(R.id.delete_button);
        addButton = findViewById(R.id.add_button);

        fileInf = findViewById(R.id.textView);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = FileName.getText().toString();
                String fileContents = FileStore.getText().toString();
                //Открываем поток для записи. Если документ не создан, то он будетmсоздан автоматически
                try (FileOutputStream fos = (MainActivity.this).openFileOutput(filename,
                        Context.MODE_PRIVATE)) {
                    //Записываем текст в файл, переведя его в массив байт
                    fos.write(fileContents.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




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

    }
}