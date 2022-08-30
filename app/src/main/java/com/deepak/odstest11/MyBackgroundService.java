package com.deepak.odstest11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyBackgroundService extends Service {

    private boolean running;

    public MyBackgroundService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "onCreate: Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand: Service Started");

        new Thread(new Runnable() {
            @Override
            public void run() {
                running=true;
                while (running) {
                    Log.d("TAG", "Service is running...");
                    //Toast.makeText(MyBackgroundService.this, "Service is running", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("TAG", ""+e.getLocalizedMessage());

                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: Service Destroy");
        running=false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}