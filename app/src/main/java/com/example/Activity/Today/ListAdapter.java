package com.example.Activity.Today;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.Bean.UserTableBean;
import com.example.Activity.Databese.table.UserTable;
import com.example.Activity.R;
import com.example.Activity.Bean.ActivityBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ListAdapter extends BaseAdapter{

    private ArrayList<ActivityBean> list;
    private Context context;
    private String url = "http://192.168.100.2:8080";

    public class ViewHolder{
        TextView Name;
        TextView subName;
        TextView Content;
        ImageView imageView;
        TextView Data;

    }

    public ListAdapter(Context context, ArrayList<ActivityBean> list){
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View v;
        final ActivityBean activityBean = list.get(position);
        if (convertView == null){
            v = View.inflate(context, R.layout.listview_content,null);
            viewHolder = new ViewHolder();
            viewHolder.Name = (TextView)v.findViewById(R.id.List_Name);//名称
            viewHolder.subName = (TextView)v.findViewById(R.id.List_subName);//职称
            viewHolder.Content = (TextView)v.findViewById(R.id.List_Content);//内容
            viewHolder.imageView = (ImageView) v.findViewById(R.id.listview_view);//头像
            viewHolder.Data = (TextView)v.findViewById(R.id.data);//日期
            v.setTag(viewHolder);
        }else {
            v = convertView;
            viewHolder = (ViewHolder)v.getTag();
        }

//        //从shared中取出数据
//        SharedPreferences sharedPreferences = this.context.getSharedPreferences("UserTable",Context.MODE_PRIVATE);
//        viewHolder.Name.setText(sharedPreferences.getString("username",""));
//        viewHolder.subName.setText(sharedPreferences.getString("subname",""));
//        System.out.println(viewHolder.Name + "/" + viewHolder.subName);

        RequestBody body = new FormBody.Builder()
                .add("userid",activityBean.getUserid())
                .build();

        final Request request = new Request.Builder()
                .url(url + "/user/search")
                .post(body)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("on failure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    viewHolder.Name.setText(json.getString("name"));
                    viewHolder.subName.setText(json.getString("subname"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        viewHolder.Content.setText(activityBean.getContent());
        viewHolder.Data.setText(activityBean.getStart_time());

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"click on "+ position +" image",Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.Content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, details_activity.class);
                intent.putExtra("name", activityBean.getName());
                intent.putExtra("text", activityBean.getContent());
                intent.putExtra("time",activityBean.getStart_time());
                if (viewHolder.imageView != null) {
                    Bitmap bitmap = ((BitmapDrawable) viewHolder.imageView.getDrawable()).getBitmap();
                    bitmap = Bitmap.createBitmap(bitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] b = baos.toByteArray();
                    intent.putExtra("photo", b);
                }
                context.startActivity(intent);
                Toast.makeText(context,"xianshixiangqing",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}