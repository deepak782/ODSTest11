package com.deepak.odstest11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class BoundActivity extends AppCompatActivity {

    TextView time;
    MyBoundService myBoundService;
    private  boolean isBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        time=findViewById(R.id.time_txt);

        Intent intent=new Intent(BoundActivity.this,MyBoundService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentTime=myBoundService.getCurrentTime();
                time.setText(""+currentTime);
            }
        });
        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);

            }
        });
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyLocalBinder myLocalBinder=(MyBoundService.MyLocalBinder) iBinder;
            myBoundService=myLocalBinder.myBoundService();
            isBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBound=false;
        }
    };
}