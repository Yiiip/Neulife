package com.lyp.neulife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lyp.neulife.R;
import com.lyp.neulife.util.ViewUtils;

public class SettingActivity extends AppCompatActivity {

    private Toolbar settingToolbar;
    private CheckBox cb1, cb2, cb3;
    private LinearLayout cbContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_setting);

        initView();
        initEvent();
    }

    private void initEvent() {

        settingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cbContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i<cbContainer.getChildCount(); i++) {
                    View view = cbContainer.getChildAt(i);

                    if (view instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) view;
                        if (checkBox.isChecked()) {
                            Toast.makeText(SettingActivity.this, checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void initView() {
        settingToolbar = (Toolbar) findViewById(R.id.settingToolbar);
        ViewUtils.setToolbarWithBackButton(this, settingToolbar);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cbContainer = (LinearLayout) findViewById(R.id.cbContainer);
    }
}
