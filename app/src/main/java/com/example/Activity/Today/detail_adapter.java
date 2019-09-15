package com.example.Activity.Today;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class detail_adapter extends FragmentPagerAdapter {
    private Context context;
    private List<String> names;
    private List<Fragment> fragments;

    public detail_adapter(FragmentManager fm,Context context,List<String> names,List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.names = names;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i){
        return names.get(i);
    }
}
