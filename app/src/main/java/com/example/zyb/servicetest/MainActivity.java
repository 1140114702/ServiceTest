package com.example.zyb.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
        Intent start = new Intent(this, MyService.class);
        startService(start);//开始服务
    }

    public void stop(View view) {
        Intent stop = new Intent(this, MyService.class);
        stopService(stop);//停止服务
    }

    public void bind(View view) {
        Intent bind = new Intent(this, MyService.class);
        bindService(bind,connection,BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(connection);
    }

    public void startIntent(View view) {
        Log.e("main", "Thread id is "+ Thread.currentThread().getId());
        Intent intent =  new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
