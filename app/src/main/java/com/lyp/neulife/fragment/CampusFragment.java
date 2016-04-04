package com.lyp.neulife.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyp.neulife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampusFragment extends Fragment {


    public CampusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_campus, container, false);
    }

}
