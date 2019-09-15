package com.example.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class modify_name extends AppCompatActivity {
    private ImageView back;
    private Button button;
    private EditText modify;
    private ImageView cencel;
    private MotionEvent ev;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceStata) {
        super.onCreate(saveInstanceStata);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.modify_name);
        initData();
    }

    public void initData(){
        back = (ImageView)findViewById(R.id.detail_back);
        button = (Button)findViewById(R.id.me_modify_name_button);
        modify = (EditText)findViewById(R.id.me_modify);
        cencel = (ImageView)findViewById(R.id.cencel);
        String name = modify.getText().toString().trim();
        int len = name.length();
        System.out.println(len);
        if(len == 3){
            cencel.setVisibility(View.GONE);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        modify.setText(sharedPreferences.getString("Name",""));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(modify_name.this,me_detail.class);
                modify_name.this.startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = modify.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Name",name);
                editor.commit();
                System.out.println(name);
                Intent intent = new Intent(modify_name.this,me_detail.class);
                modify_name.this.startActivity(intent);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v,MotionEvent event){
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
                if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
