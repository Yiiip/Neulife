package com.lyp.neulife.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lyp.neulife.R;
import com.lyp.neulife.util.ViewUtils;

public class SplashActivity extends BaseActivity {

    private static final int TO_HOME = 1;
    private static final int TO_LOGIN = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_HOME:
                    startAnimActivity(MainActivity.class);
                    finish();
                    break;
                case TO_LOGIN:
//                    startAnimActivity(LoginActivity.class);
                    finish();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_splash);

        //判断是否登陆
        //...
        mHandler.sendEmptyMessageDelayed(TO_HOME, 2000);
    }
}
