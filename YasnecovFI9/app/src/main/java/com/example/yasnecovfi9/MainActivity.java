package com.example.yasnecovfi9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText FileName, FileStore;
    Button saveButton, readButton, deleteButton, addButton;
    TextView fileInf;
    String filename;

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
        Context context = MainActivity.this;



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                String fileContents = FileStore.getText().toString() ;
                //Открываем поток для записи. Если документ не создан, то он будетmсоздан автоматически
                try (FileOutputStream fos = context.openFileOutput(filename,
                        Context.MODE_PRIVATE)) {
                    //Записываем текст в файл, переведя его в массив байт
                    fos.write(fileContents.getBytes());
                    Toast.makeText(MainActivity.this,"Файл создан",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                try (FileInputStream fis = context.openFileInput(filename)) {
                    InputStreamReader inputStreamReader = new
                            InputStreamReader(fis, StandardCharsets.UTF_8);
                    StringBuilder stringBuilder = new StringBuilder();
                    try (BufferedReader reader = new
                            BufferedReader(inputStreamReader)) {
                        String line = reader.readLine();
                        while (line != null) {
                            stringBuilder.append(line).append('\n');
                            line = reader.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String contents = stringBuilder.toString();
                    fileInf.setText(contents);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Нет файла с таким именем",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                filename = FileName.getText().toString() + ".txt";
                // Установка заголовка и сообщения диалогового окна
                builder.setTitle("Подтверждение");
                builder.setMessage("Вы уверены, что хотите удалить "+ filename +  " ?" );
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                // Установка кнопки "Да" и ее обработчика
                builder.setPositiveButton("Да", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Получаем файловый объект для файла из внутреннего хранилища
                                File dir = getFilesDir();
                                File file = new File(dir, filename);
                                // Удаляем файл
                                boolean deleted = file.delete();
                                if (deleted){
                                    Toast.makeText(MainActivity.this,"Файл удален",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Ошибка при удалении",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                // Установка кнопки "Отмена" и ее обработчика
                builder.setNegativeButton("Отмена", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Обработка отмены действия
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = FileName.getText().toString() + ".txt";
                String fileContents = FileStore.getText().toString() ;
                //Открываем поток для записи. Если документ не создан, то он будет создан автоматически
                // МОDE_APPEND - дозаписать в файл
                try (FileOutputStream fos = context.openFileOutput(filename,
                        Context.MODE_APPEND)) {
                    //Записываем текст в файл, переведя его в массив байт
                    fos.write(fileContents.getBytes());
                    Toast.makeText(MainActivity.this,"Текст дописан",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        fileInf.setText(state);

    }
}