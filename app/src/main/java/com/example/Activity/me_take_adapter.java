package com.example.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class me_take_adapter extends FragmentPagerAdapter {
    private Context context;
    private List<String> names;
    private List<Fragment> fragments;

    public me_take_adapter(Context context, List<String> names, List<Fragment> fragments, FragmentManager fm){
        super(fm);
        this.context = context;
        this.names = names;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public CharSequence getPageTitle(int i){
        return names.get(i);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }
}
