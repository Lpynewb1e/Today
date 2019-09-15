package com.example.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Toast;

import com.example.Activity.Databese.DB;
import com.example.Activity.Today.TodayActivity;


public class MainActivity extends AppCompatActivity{
    private TodayActivity todayactivity;
    private BottomNavigationView bottomNavigationView;
    private lost_and_found lost_and_found;
    private me me;
    private Fragment[] fragments;//fragment数组
    private int lastfragment;//fragment序号
    private Toolbar toolbar;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initFragment();
        initTitle();
    }
    public void initTitle(){
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);//匹配标题栏
        toolbar.inflateMenu(R.menu.toolbar_menu);//匹配标题栏的菜单
        toolbar.setOverflowIcon(getResources().getDrawable(android.R.drawable.ic_menu_add));//设置右边的图标
        //设置菜单的item监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.add_activity:
                        Intent intent = new Intent(MainActivity.this,add_activity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"add ActivityBean",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.add_lost:
                        Toast.makeText(MainActivity.this,"add lost",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    //初始化fragment
    private void initFragment(){
        todayactivity = new TodayActivity();
        lost_and_found = new lost_and_found();
        me = new me();
        fragments = new Fragment[]{todayactivity,lost_and_found,me};
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view,todayactivity).show(todayactivity).commit();
        lastfragment = 0;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.Bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    //底部导航栏按钮事件监听
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.id_1:
                {
                    if (lastfragment!=0){
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.id_2:
                {
                    if (lastfragment!=1){
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
                }
                case R.id.id_3:
                {
                    if(lastfragment!=2){
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    //当按钮事件触发，会转到另一个页面
    private void switchFragment(int lastfragment,int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//把刚才的fragment隐藏
        if(fragments[index].isAdded() == false){
            transaction.add(R.id.main_view,fragments[index]);//把新来的fragment加入到主页面上
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();//显示刚才加入的fragment
    }
}
