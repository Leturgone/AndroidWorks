package com.example.yasnecovfi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Yasnecov FI IKBO-07-22";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"Error in onCreate");
        Log.w(TAG,"Warning in onCreate");
        Log.i(TAG,"Info in onCreate");
        Log.d(TAG,"Info in onCreate");
        Log.v(TAG,"Verbose in onCreate");
        Button ProgButton = findViewById(R.id.prog_button);
        ProgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Нажатие на кнопку
                Toast.makeText(getApplicationContext(),"Програмная кнопка нажата ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        }
        );

    }
    public void onNextActivity(View view){
        // выводим сообщение
        Toast.makeText(this, "Декларативная кнопка нажата", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);

    }

    public void onRegActivity(View view){
        //Получение ссылок на виджеты
        EditText nameText = findViewById(R.id.FIOtext);
        EditText groupText = findViewById(R.id.GroupText);
        EditText ageText = findViewById(R.id.Yeartext);
        EditText markText = findViewById(R.id.Marktext);

        //Получение значений
        String name = nameText.getText().toString();
        String group = groupText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        int mark = Integer.parseInt(markText.getText().toString());

        //Передача данных в RegActivity
        Intent intent = new Intent(this, RegActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("group",group);
        intent.putExtra("age",age);
        intent.putExtra("mark",mark);

        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"Error in onStart");
        Log.w(TAG,"Warning in onStart");
        Log.i(TAG,"Info in onStart");
        Log.d(TAG,"Info in onStart");
        Log.v(TAG,"Verbose in onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"Error in onResume");
        Log.w(TAG,"Warnigatng in onResume");
        Log.i(TAG,"Info in onResume");
        Log.d(TAG,"Info in onResume");
        Log.v(TAG,"Verbose in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"Error in onPause");
        Log.w(TAG,"Warning in onPause");
        Log.i(TAG,"Info in onPause");
        Log.d(TAG,"Info in onPause");
        Log.v(TAG,"Verbose in onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"Error in onStop");
        Log.w(TAG,"Warning in onStop");
        Log.i(TAG,"Info in onStop");
        Log.d(TAG,"Info in onStop");
        Log.v(TAG,"Verbose in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"Error in onDestroy");
        Log.w(TAG,"Warning in onDestroy");
        Log.i(TAG,"Info in onDestroy");
        Log.d(TAG,"Info in onDestroy");
        Log.v(TAG,"Verbose in onDestroy");
    }
}