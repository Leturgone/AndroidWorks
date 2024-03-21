package com.example.ysnecovfi3v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView message = (TextView) findViewById(R.id.new_message);

        MyObject objectInput = (MyObject) getIntent().getSerializableExtra("object");

        if(objectInput!=null){
            String username = getString(R.string.user_name) + ": ";
            String userage = getString(R.string.user_age) + ": ";
            message.setText(username + objectInput.getName() + "\n" +  userage + objectInput.getAge());
        }

        Button BackButton = findViewById(R.id.back_button);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}