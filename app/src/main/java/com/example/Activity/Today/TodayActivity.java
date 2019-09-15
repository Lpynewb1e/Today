package com.example.Activity.Today;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Activity.R;

import java.util.ArrayList;
import java.util.List;


public class TodayActivity extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> names = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todayactivity,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
        tabLayout = (TabLayout) getActivity().findViewById(R.id.today_tab);//匹配tabLayout
        viewPager = (ViewPager) getActivity().findViewById(R.id.today_viewpager);//匹配响应viewPager
        viewPager.setAdapter(new TodayActivityAdapter(names,fragments,getFragmentManager(),getContext()));//给viewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//把viewPager和tablayout绑定
    }

    //初始化顶部导航栏
    public void initDatas(){
        AlreadyStart alreadyStart = new AlreadyStart();
        fragments.add(alreadyStart);
        names.add("未结束");
        HasEnded hasEnded = new HasEnded();
        fragments.add(hasEnded);
        names.add("已结束");
    }
}
