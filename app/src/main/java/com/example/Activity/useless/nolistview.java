package com.example.Activity.useless;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


public class nolistview extends ListView{

    public nolistview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 动态计算高度
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
