package com.lyp.neulife.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lyp.neulife.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lyp on 2016/3/19.
 */
public class BaseActivity extends AppCompatActivity {

    static List<BaseActivity> baseActivityList = new LinkedList<>();

    private KillAllActivityReceiver receiver;

    private static final String KILL_ALL_ACTIVITY_ACTION = "lyp.neulife.KILL_ALL";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        synchronized (baseActivityList) {
            receiver = new KillAllActivityReceiver();
            IntentFilter filter = new IntentFilter(KILL_ALL_ACTIVITY_ACTION);
            registerReceiver(receiver, filter);
            baseActivityList.add(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (baseActivityList) {
            baseActivityList.remove(this);
        }

        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }


    public void killAllActivity() {
        List<BaseActivity> copyList;
        synchronized (baseActivityList) {
            copyList = new LinkedList<>(baseActivityList);
        }
        for (BaseActivity activity : copyList) {
            activity.finish();
        }
    }


    class KillAllActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }


    public void startAnimActivity(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }

    public void startAnimActivity(Intent intent) {
        this.startActivity(intent);
    }
}
