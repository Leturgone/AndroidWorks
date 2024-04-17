package com.example.yasnecovfi8;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyThirdWorker extends Worker {
    public final String TAG = "WORKER3_TAG";
    public MyThirdWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.v(TAG, "Third work is in progress");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Third work finished");
        return Worker.Result.success();
    }
}
