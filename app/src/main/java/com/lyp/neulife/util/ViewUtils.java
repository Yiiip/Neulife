package com.lyp.neulife.util;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

/**
 * Created by lyp on 2016/3/22.
 */
public class ViewUtils {

    /**
     * Android 4.4 以上的版本实现系统状态栏透明
     * @param activity
     */
    public static void setTranslucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 设置带有返回按钮的Toolbar
     * @param activity
     * @param toolbar
     */
    public static void setToolbarWithBackButton(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public static void removeSelfFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
        }
    }
}
