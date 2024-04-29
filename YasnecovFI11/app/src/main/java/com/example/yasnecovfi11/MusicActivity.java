package com.example.yasnecovfi11;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button fun_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("URL вашего аудиофайла");
                    mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fun_button = findViewById(R.id.fun_button);
        fun_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                else{
                    mediaPlayer.stop();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            //освобождение ресурсов
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
