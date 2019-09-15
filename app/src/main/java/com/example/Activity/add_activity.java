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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Activity.Bean.ActivityBean;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class add_activity extends AppCompatActivity {
    private ImageView imageView;
    private EditText AddTitle;
    private EditText AddContent;
    private EditText AddLocation;
    private TextView Time;
    private TextView Deadline;
    private TextView AddComplete;
    private OkHttpClient okHttpClient;
    private String url = "http://192.168.100.2:8080";
    private ArrayList<ActivityBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_activity);
        initData();
    }

    public void initData(){
        imageView = (ImageView)findViewById(R.id.detail_back);//返回
        AddTitle = (EditText)findViewById(R.id.add_title);//标题
        AddContent = (EditText)findViewById(R.id.add_content);//内容详情
        AddLocation = (EditText)findViewById(R.id.add_location);//位置
        AddComplete = (TextView)findViewById(R.id.me_modify_name_button);//完成添加
        Deadline = (TextView)findViewById(R.id.deadline);//截止日期
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_activity.this.finish();
            }
        });
        AddComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HttpPost();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(add_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
     * 发送请求 存储新建活动
     */

    public boolean HttpPost() throws IOException{
        //获取时间日期
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        //从shared中取值
        SharedPreferences sharedPreferences = getSharedPreferences("UserTable",MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");




        //给活动赋值
        final ActivityBean bean = new ActivityBean();
        bean.setTitle(AddTitle.getText().toString());
        bean.setContent(AddContent.getText().toString());
        bean.setAddress(AddLocation.getText().toString());
        bean.setDeadline(Deadline.getText().toString());
        bean.setStatus(1);
        bean.setUserid(id);
        bean.setStart_time(currentTime);


        //发送请求
        okHttpClient = new OkHttpClient();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        RequestBody requestBody = RequestBody.create(json,JSON);
        Request request = new Request.Builder()
                .url(url + "/activity/add")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("false");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("true");
            }
        });
        return true;
    }




    //点击Edittext 之外的地方 软键盘消失
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
