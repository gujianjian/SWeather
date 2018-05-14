package com.example.joy.sweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.joy.sweather.utils.L;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        L.d("开始自动更新");
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        long updatetime=SystemClock.elapsedRealtime()+2000;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,updatetime,pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
