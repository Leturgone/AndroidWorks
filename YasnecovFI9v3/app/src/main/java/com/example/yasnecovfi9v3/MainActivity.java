package com.example.yasnecovfi9v3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText FileName, FileStore;
    Button saveButton, deleteButton, addButton;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileName = findViewById(R.id.editFileNameText);
        FileStore = findViewById(R.id.editFileText);

        saveButton = findViewById(R.id.save_button);
        deleteButton = findViewById(R.id.delete_button);
        addButton = findViewById(R.id.add_button);

        Context context = MainActivity.this;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                String fileContents = FileStore.getText().toString() ;
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, filename);
                // Для записи в файл
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(fileContents.getBytes());
                    Toast.makeText(MainActivity.this, "Файл создан", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                String fileContents = FileStore.getText().toString() ;
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                if (dir.exists()) {
                    File file = new File(dir, filename);
                    // Для удаления файла
                    boolean deleted = file.delete();
                    if (deleted) {
                        Toast.makeText(MainActivity.this, "Файл удален", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Ошибка при удалении", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Директория не найдена", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                String fileContents = FileStore.getText().toString() ;
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                if (dir.exists()) {
                    File file = new File(dir, filename);
                    try (FileOutputStream fos = new FileOutputStream(file, true)) {
                        //Записываем текст в файл, переведя его в массив байт
                        fos.write(fileContents.getBytes());
                        Toast.makeText(MainActivity.this, "Текст дописан", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}