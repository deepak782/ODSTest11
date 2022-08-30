package com.deepak.odstest11;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {

    private IBinder iBinder=new MyLocalBinder();

    public class MyLocalBinder extends Binder{

        MyBoundService myBoundService(){
            return MyBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TAG", "onBind: Bind the Service");
        return iBinder;
    }

    public String getCurrentTime()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: UnBind the Service");
    }
}
