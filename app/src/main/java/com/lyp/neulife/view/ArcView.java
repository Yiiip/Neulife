package com.lyp.neulife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by lyp on 2016/3/27.
 */
public class ArcView extends LinearLayout{

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
