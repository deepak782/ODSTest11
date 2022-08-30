package com.deepak.odstest11;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyForegroundService extends Service {



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotifiction();
        Intent intent1=new Intent(this,Foreground1Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);

        Notification notification=new Notification.Builder(this,"Channel1")
                .setContentText("your application is running")
                .setContentTitle("Foreground Service")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1,notification);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotifiction() {
        if(Build.VERSION.SDK_INT>=8)
        {
            NotificationChannel notificationChannel=new NotificationChannel("Channel1","Foreground Service", NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        stopSelf();
    }
}
