package com.example.Activity.Today;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.R;
import com.example.Activity.take_in;

import java.util.ArrayList;
import java.util.List;


public class details_activity extends AppCompatActivity {
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
    private ImageView back;
    private ImageView Photo;
    private TextView Name;
    private TextView SubName;
    private TextView Content;
    private TextView Time;
    private ScrollView scrollView;
    private List<String> names = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private Button button;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.details_view);
//        initData();
        transform_datas();
        init();//申明本Activity 中的所有控件
        setListen();//监听
    }

    public void init(){
        button = (Button)findViewById(R.id.detail_button);//参加活动
        back = (ImageView)findViewById(R.id.detail_back);//返回
    }

    public void setListen(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(details_activity.this,"asfasf",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(details_activity.this, take_in.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(details_activity.this,"sdf",Toast.LENGTH_SHORT).show();
                details_activity.this.finish();
            }
        });
        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(details_activity.this,"af",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void transform_datas(){
        Photo = (ImageView)findViewById(R.id.me_photo);
        Name = (TextView)findViewById(R.id.me_name);
        SubName = (TextView)findViewById(R.id.me_id);
        Content = (TextView)findViewById(R.id.me_content);
        Time = (TextView)findViewById(R.id.data);
        String name = getIntent().getStringExtra("name");
        String other_name = getIntent().getStringExtra("subname");
        String text = getIntent().getStringExtra("text");
        String data = getIntent().getStringExtra("time");
        Name.setText(name);
        SubName.setText(other_name);
        Content.setText(text);
        Time.setText(data);
        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("photo");
        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        Photo.setImageBitmap(bitmap);
    }

}
