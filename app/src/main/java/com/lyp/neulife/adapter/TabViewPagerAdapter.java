package com.lyp.neulife.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lyp.neulife.fragment.FragmentFactory;
import com.lyp.neulife.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2016/3/19.
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter{

    private final List<String> mFragmentsTitles = new ArrayList<>();
    private Context mContext;

    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentsTitles.add("首页");
        mFragmentsTitles.add("校园");
        mFragmentsTitles.add("教务");
    }

    public TabViewPagerAdapter(FragmentManager fm, Context context) {
        this(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mFragmentsTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitles.get(position);
    }
}
