package com.example.yasnecovfi8;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MySecondWorker extends Worker {
    public final String TAG = "WORKER2_TAG";
    public MySecondWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.v(TAG, "Second work is in progress");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Second work finished");
        return Worker.Result.success();
    }
}
