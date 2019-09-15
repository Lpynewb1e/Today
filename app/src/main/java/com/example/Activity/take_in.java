package com.example.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class take_in extends AppCompatActivity {
    private Button add;
    private LinearLayout llll;
    private ImageView back;
    private LayoutInflater inflater;
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private String name;
    private String id;
    private String phone;
    private TextView textView;
    private List<String> names = new ArrayList<>();
    private List<String> ids = new ArrayList<>();
    private List<String> phones = new ArrayList<>();
    private Button save;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceStata){
        super.onCreate(saveInstanceStata);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.take_in);
        initData();
    }

    public void initData(){
        llll = (LinearLayout)findViewById(R.id.llll);
        add = (Button)findViewById(R.id.add_people);
        back = (ImageView)findViewById(R.id.detail_back);
        save = (Button)findViewById(R.id.save_people);
        e1 = new EditText(this);
        e2 = new EditText(this);
        e3 = new EditText(this);
        e1.setHint("姓名");
        e2.setHint("学号");
        e3.setHint("手机号");
        e1.setLeft(20);
        e2.setLeft(20);
        e3.setLeft(20);
        llll.addView(e1);
        llll.addView(e2);
        llll.addView(e3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take_in.this.finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = e1.getText().toString();
                id = e2.getText().toString();
                phone = e3.getText().toString();
                names.add(name);
                ids.add(id);
                phones.add(phone);
                System.out.println(name);
                System.out.println(id);
                System.out.println(phone);
                textView = new TextView(v.getContext());
                textView.setWidth(2);
                textView.setTextColor(Color.parseColor("#BFBFBF"));
                llll.addView(textView);
                e1 = new EditText(v.getContext());
                e2 = new EditText(v.getContext());
                e3 = new EditText(v.getContext());
                e1.setHint("姓名");
                e2.setHint("学号");
                e3.setHint("手机号");
                llll.addView(e1);
                llll.addView(e2);
                llll.addView(e3);
                Toast.makeText(take_in.this,"a",Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = e1.getText().toString();
                id = e2.getText().toString();
                phone = e3.getText().toString();
                names.add(name);
                ids.add(id);
                phones.add(phone);
                for (int i=0;i<names.size();i++){
                    System.out.println("name:"+names.get(i));
                    System.out.println("id:"+ids.get(i));
                    System.out.println("phone:"+phones.get(i));
                }
                take_in.this.finish();
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
