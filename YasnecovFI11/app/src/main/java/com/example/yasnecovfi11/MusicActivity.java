package com.example.yasnecovfi11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button fun_button;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        imageView = findViewById(R.id.imageView);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.televisiontunes.co.uk/themes/Benny%20Hill.mp3");
                    mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fun_button = findViewById(R.id.fun_button);
        fun_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.omg));
                    ObjectAnimator rotateAnim =
                            ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                    rotateAnim.setDuration(800);
                    rotateAnim.setRepeatCount(ObjectAnimator.INFINITE);
                    rotateAnim.setRepeatMode(ObjectAnimator.RESTART);
                    rotateAnim.start();
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
