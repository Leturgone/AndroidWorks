package com.example.yasnecovfi8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button PosledBtn, ParalelBtn, DownloadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PosledBtn = findViewById(R.id.posled_btn);
        ParalelBtn = findViewById(R.id.paralel_btn);
        DownloadBtn = findViewById(R.id.download_button);

        OneTimeWorkRequest work1 =
                new OneTimeWorkRequest.Builder(MyWorker.class).build();
        OneTimeWorkRequest work2 =
                new OneTimeWorkRequest.Builder(MySecondWorker.class).build();
        OneTimeWorkRequest work3 =
                new OneTimeWorkRequest.Builder(MyThirdWorker.class).build();
        PosledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().beginWith(work1).then(work2).then(work3).enqueue();
            }
        });

        ParalelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<OneTimeWorkRequest> list = new ArrayList<>();
                list.add(work1);
                list.add(work2);
                WorkManager.getInstance().enqueue(list);

            }
        });
        DownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}