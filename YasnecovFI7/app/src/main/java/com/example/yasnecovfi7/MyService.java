package com.example.yasnecovfi7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;
    @Override
    public void onCreate(){

        //onCreate() вызывается при создании сервиса

        super.onCreate();
        //Инициализация медиаплеера
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.setLooping(true);//зацикливание воспроизведения
        mediaPlayer.setVolume(100,100);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        //onStartCommand() вызывается каждый раз, когда компонент (например,
        //активность) запрашивает запуск сервиса через startService().

        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            Log.d(TAG,"Музыка начала играть");
        }
        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        //onDestroy() вызывается, когда сервис больше не используется и
        //собирается быть уничтоженным.

        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();//освобождение ресурсов
            Log.d(TAG,"Музыка остановлена и ресурсы особождены");
        }
    }


    @Override
    public IBinder onBind(Intent intent) {

        //Метод onBind() вызывается, когда другой компонент хочет привязаться к
        //сервису через bindService().

        return null; // Для сервисов без привязки возвращаем null
    }
}