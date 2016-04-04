package com.lyp.neulife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.lyp.neulife.R;
import com.lyp.neulife.util.ViewUtils;

public class SearchActivity extends AppCompatActivity {

    private Toolbar searchToolbar;
    private EditText searchEditText;
    private ListView searchListView;

    private String[] datas;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_search);

        initView();
        initData();
        initEvent();
    }

    private void initData() {
    }

    private void initEvent() {
        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        searchToolbar = (Toolbar) findViewById(R.id.searchToolbar);
        ViewUtils.setToolbarWithBackButton(this, searchToolbar);

        searchEditText = (EditText) findViewById(R.id.searchEditTextView);

        searchListView = (ListView) findViewById(R.id.searchListView);
    }
}
