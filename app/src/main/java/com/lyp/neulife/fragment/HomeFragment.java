package com.lyp.neulife.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyp.neulife.R;
import com.lyp.neulife.adapter.AdapterWithBanner;
import com.lyp.neulife.adapter.HomeRcAdapter;
import com.lyp.neulife.bean.BeanNews;
import com.lyp.neulife.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView homeRcView;
    private HomeRcAdapter rcAdapter;

    private SwipeRefreshLayout homeSwipeRefreshLayout;

    private List<BeanNews> newsDatas;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initEvent() {
        homeSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        newsDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsDatas.add(new BeanNews("第 "+i+" 条新闻资讯"));
        }

        rcAdapter = new HomeRcAdapter(getContext(), newsDatas);
        homeRcView.setAdapter(rcAdapter);
    }

    private void initView(View view) {
        homeRcView = (RecyclerView) view.findViewById(R.id.homeRcView);
        homeRcView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        homeRcView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        homeSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.homeSwipeRefreshLayout);
        homeSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#03a9f4"), Color.parseColor("#2196f3"));

    }


    @Override
    public void onRefresh() {
        homeSwipeRefreshLayout.setRefreshing(true);
    }
}
