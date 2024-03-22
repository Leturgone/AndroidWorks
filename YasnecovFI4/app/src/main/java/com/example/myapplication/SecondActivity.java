package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Получаем данные
        TextView message_name = (TextView) findViewById(R.id.text_name_info);
        TextView message_surname = (TextView) findViewById(R.id.text_surname_info);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
           String name = arguments.get("name").toString();
           String surname = arguments.get("surname").toString();
           message_name.setText("Имя: " + name);
           message_surname.setText("Фамилия: " + surname);
        }
        Button VVButton = findViewById(R.id.enter_button);



    }

    public void onThirdActivity(View view){
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        mStartForResult.launch(intent);
    }
    ActivityResultLauncher<Intent> mStartForResult =
            registerForActivityResult(new
                            ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            TextView message_date = (TextView) findViewById(R.id.date_text_info);
                            if(result.getResultCode() == Activity.RESULT_OK){
                                Intent intent = result.getData();
                                String date = intent.getStringExtra("date");
                                String time = intent.getStringExtra("time");
                                message_date.setText("Дата занятия: " + date + " " + time);
                                Toast.makeText(SecondActivity.this,"Данные о дате переданы успешно",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(SecondActivity.this,"Нет данных",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

}
