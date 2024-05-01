package com.example.yasnecovfi11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button fun_button;
    private ImageView imageView;
    public static final String CHANNEL_ID = "funny_channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        createNotificationChannel();
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

                    NotificationCompat.Builder builder = new
                            NotificationCompat.Builder(MusicActivity.this, CHANNEL_ID).setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle("Уведомление").setContentText("Началось веселье").setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.notify(1, builder.build());

                    scheduleNotification(10000);

                    imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.omg));
                    ObjectAnimator rotateAnim =
                            ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                    rotateAnim.setDuration(800);
                    rotateAnim.setRepeatCount(ObjectAnimator.INFINITE);
                    rotateAnim.setRepeatMode(ObjectAnimator.RESTART);

                    ObjectAnimator scaleX =
                            ObjectAnimator.ofFloat(fun_button, "scaleX", 1f, 2f);
                    ObjectAnimator scaleY =
                            ObjectAnimator.ofFloat(fun_button, "scaleY", 1f, 2f);
                    scaleX.setDuration(100);
                    scaleY.setDuration(150);
                    scaleX.start();
                    scaleY.start();
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
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Funny Channel";
            String description = "Channel for funny notifications";
            int importance =
                    NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
    private void scheduleNotification(long delay) {
        Intent notificationIntent = new Intent(this,
                AlarmReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT |
                                PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)
                getSystemService(ALARM_SERVICE);
        long futureInMillis = System.currentTimeMillis() +
                delay;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }


}
