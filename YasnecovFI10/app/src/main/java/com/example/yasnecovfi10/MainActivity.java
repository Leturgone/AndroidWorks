package com.example.yasnecovfi10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button get_button, change_button, del_button,on_next_button;
    TextView user_data;
    EditText editText;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_button = findViewById(R.id.get_button);
        change_button = findViewById(R.id.change_button);
        del_button = findViewById(R.id.del_button);
        on_next_button = findViewById(R.id.next_button);

        user_data = findViewById(R.id.user_data);
        editText = findViewById(R.id.editTextText);
        sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = sharedPreferences.getString("username","defaultUsername");
                if (!userName.equals("defaultUsername")) {
                    user_data.setText(userName);
                }
                else{
                    Toast.makeText(MainActivity.this, "Нечего получать", Toast.LENGTH_SHORT).show();
                }
            }
        });
        change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String user = editText.getText().toString();
                if(!user.equals("")) {
                    editor.putString("username", user);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Вы ничего не ввели", Toast.LENGTH_SHORT).show();
                }
            }
        });
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Удаление данных
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Удаление данных по ключу
                editor.remove("username");
                // Удаление всех данных
                editor.clear();
                // Применение изменений
                editor.apply();
                Toast.makeText(MainActivity.this, "Данные удалены", Toast.LENGTH_SHORT).show();
            }
        });
        on_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaseActivity.class);
                startActivity(intent);
            }
        });

    }
}