package com.lyp.neulife.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;

/**
 * Created by lyp on 2016/3/19.
 */
public class FragmentFactory {

    private static final String TAG = FragmentFactory.class.getSimpleName();

    private static ArrayMap<Integer, Fragment> fragmentArrayMap = new ArrayMap<>();

    public static Fragment createFragment(int position) {

        Fragment fragment;
        fragment = fragmentArrayMap.get(position);

        if (fragment == null) {
            Log.e(TAG, "createFragment " + "Fragment为null执行");

            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new CampusFragment();
            } else if (position == 2) {
                fragment = new EduAdminFragment();
            }

            if (fragment != null) {
                fragmentArrayMap.put(position, fragment);
            }
        }
        return fragment;

    }
}
