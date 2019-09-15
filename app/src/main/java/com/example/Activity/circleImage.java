package com.example.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class circleImage extends ImageView {

    private int Radius;

    public circleImage(Context context) {
        super(context);
    }

    public circleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public circleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int width,int height){
        super.onMeasure(width,height);
        int size = Math.min(getMeasuredWidth(),getMeasuredHeight());
        Radius = size/2;
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint mpaint = new Paint();
    }
}
