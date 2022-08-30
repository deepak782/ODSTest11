package com.deepak.broadcastapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String actionString=intent.getAction();
        String timeZone= intent.getStringExtra("time-zone");
        Toast.makeText(context, ""+actionString+"\n"+timeZone, Toast.LENGTH_SHORT).show();

        boolean state= intent.getBooleanExtra("state",false);
        Log.d("TAG", "onReceive: airplane mode in On:"+state);




    }
}