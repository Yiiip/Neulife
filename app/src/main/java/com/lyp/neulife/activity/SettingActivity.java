package com.lyp.neulife.activity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lyp.neulife.R;
import com.lyp.neulife.util.ViewUtils;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    private Toolbar settingToolbar;
    private EditText editTime;
    private Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setTranslucentStatusBar(this);
        setContentView(R.layout.activity_setting);

        editTime = (EditText) findViewById(R.id.editTime);
        editTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                new TimePickerDialog(SettingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String strHourOfDay = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                String strMinute = minute < 10 ? "0" + minute : "" + minute;
                                editTime.setText(strHourOfDay + ":" + strMinute);
                            }
                        }, mHour, mMinute, true).show();
            }
        });

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

    }

    private void initView() {
        settingToolbar = (Toolbar) findViewById(R.id.settingToolbar);
        ViewUtils.setToolbarWithBackButton(this, settingToolbar);

    }
}
