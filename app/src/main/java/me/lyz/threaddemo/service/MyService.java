package me.lyz.threaddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author LYZ 2018.08.26
 */
public class MyService extends Service {
    private MyBinder mBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends Binder {
        private static final String TAG = "MyBinder";

        public void logInfo() {
            Log.d(TAG, "logInfo");
        }
    }
}
