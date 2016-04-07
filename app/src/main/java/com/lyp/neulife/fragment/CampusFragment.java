package com.lyp.neulife.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lyp.neulife.R;
import com.lyp.neulife.activity.MovieActivity;
import com.lyp.neulife.adapter.GridWithImgAdapter;
import com.lyp.neulife.bean.BeanCampusGrid;

import java.util.ArrayList;
import java.util.List;

public class CampusFragment extends Fragment {

    private GridView campusGridView;
    private List<BeanCampusGrid> campusGridBeans = new ArrayList<>();

    public CampusFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus, container, false);

        initView(view);
        initData();
        initEvent(view);

        return view;
    }

    private void initEvent(View view) {
        campusGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        getContext().startActivity(new Intent(getContext(), MovieActivity.class));
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        BeanCampusGrid email = new BeanCampusGrid("邮件",R.drawable.icon_email_64);
        campusGridBeans.add(email);
        BeanCampusGrid deng = new BeanCampusGrid("42电影",R.drawable.icon_email_64);
        campusGridBeans.add(deng);
        BeanCampusGrid kong = new BeanCampusGrid("教室查询",R.drawable.icon_email_64);
        campusGridBeans.add(kong);
        BeanCampusGrid daohang = new BeanCampusGrid("校园导航",R.drawable.icon_email_64);
        campusGridBeans.add(daohang);
        BeanCampusGrid tushu = new BeanCampusGrid("图书馆",R.drawable.icon_email_64);
        campusGridBeans.add(tushu);
        BeanCampusGrid jianzhi = new BeanCampusGrid("兼职信息",R.drawable.icon_email_64);
        campusGridBeans.add(jianzhi);
        BeanCampusGrid baoxiu = new BeanCampusGrid("网络报修",R.drawable.icon_email_64);
        campusGridBeans.add(baoxiu);
        BeanCampusGrid zaixian = new BeanCampusGrid("在线学习",R.drawable.icon_email_64);
        campusGridBeans.add(zaixian);
        BeanCampusGrid ershou = new BeanCampusGrid("二手市场",R.drawable.icon_email_64);
        campusGridBeans.add(ershou);

        GridWithImgAdapter adapter = new GridWithImgAdapter(getContext(), R.layout.item_grid_campus, campusGridBeans);
        campusGridView.setAdapter(adapter);
    }

    private void initView(View view) {
        campusGridView = (GridView) view.findViewById(R.id.campusGridView);
    }

}
