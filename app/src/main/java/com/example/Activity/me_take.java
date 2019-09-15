package com.example.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.Activity.Today.HasEnded;
import com.example.Activity.Today.AlreadyStart;

import java.util.ArrayList;
import java.util.List;

public class me_take extends AppCompatActivity {
    private List<String> names = new ArrayList<>();
    private List<Fragment> fragments= new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private me_take_adapter me_take_adapter;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_take);
        initData();
        back = (ImageView)findViewById(R.id.detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me_take.this.finish();
            }
        });
        tabLayout = (TabLayout)findViewById(R.id.me_tableyout);
        viewPager = (ViewPager)findViewById(R.id.me_viewpager);
        me_take_adapter = new me_take_adapter(getBaseContext(),names,fragments,getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(me_take_adapter);
    }

    public void initData(){
        AlreadyStart runaway = new AlreadyStart();
        names.add("未结束");
        fragments.add(runaway);
        HasEnded lecture_list = new HasEnded();
        names.add("已结束");
        fragments.add(lecture_list);
    }
}
