package com.example.Activity.Today;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.Activity.Bean.ActivityBean;
import com.example.Activity.Bean.ContentTableBean;
import com.example.Activity.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HasEnded extends Fragment {
    private ListView listView;
    private ListAdapter list_ac_adapter;
    private ArrayList<ActivityBean> activities = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ContentTableBean> list = new ArrayList<>();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String url = "http://192.168.100.2:8080";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.lecture_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        listView.addHeaderView(new View(getContext()));
        listView.addFooterView(new View(getContext()));
        initData();
        list_ac_adapter = new ListAdapter(getActivity(),activities);
        listView.setAdapter(list_ac_adapter);
        /*
         * 刷新
         */
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            int k=1;
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        activities.get(activities.size());
//                        ListAdapter.notifyDataSetChanged();
//                        swipeRefreshLayout.setRefreshing(true);
//                    }
//                 }, 800);
//            }
//        });
    }

    /*
     * 初始化控件
     */
    public void init(){
        listView = (ListView)getView().findViewById(R.id.HasEndedListView);//HasEnded 中的列表
        swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.swiperefreshlayout);//HasEnded 中的刷新控件
    }

    public void initData(){
        //获取时间
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);


        //构造postbody
        RequestBody body = new FormBody.Builder()
                .add("time",currentTime)
                .build();

        //发送请求
        final Request request = new Request.Builder()
                .url(url + "/activity/end")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(getTag(),"on failure : " );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.d(getTag(), "onResponse: " + response.body().string());
                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonElements = jsonParser.parse(response.body().string()).getAsJsonArray();//获取json数组
                ArrayList<ActivityBean> beans = new ArrayList<>();
                for(int i=0;i<jsonElements.size();i++){
                    ActivityBean a1 = gson.fromJson(jsonElements.get(i), ActivityBean.class);
                    beans.add(a1);
                    activities.add(a1);
                }
            }
        });
    }
}
