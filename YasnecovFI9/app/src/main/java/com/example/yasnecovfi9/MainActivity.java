package com.example.yasnecovfi9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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