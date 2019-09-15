//package com.example.TodayActivity;
//
//import android.content.Context;
//import android.support.design.widget.TabLayout;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class like_adapter extends BaseAdapter {
//    private ArrayList<like_people> list;
//    private Context context;
//
//    public like_adapter(Context context,ArrayList<like_people> list){
//        this.context = context;
//        this.list = list;
//    }
//
//    public class ViewHolder{
//        ImageView like_photo;
//        TextView like_name;
//        TextView like_job_title;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        View v;
//        like_people like_people = list.get(position);
//        if (convertView == null){
//            v = View.inflate(context,R.layout.like_people,null);
//            viewHolder = new ViewHolder();
//            viewHolder.like_photo = (ImageView)v.findViewById(R.id.like_photo);
//            viewHolder.like_name = (TextView)v.findViewById(R.id.like_name);
//            viewHolder.like_job_title = (TextView)v.findViewById(R.id.like_job_title);
//            v.setTag(viewHolder);
//        }
//        else {
//            v = convertView;
//            viewHolder = (ViewHolder) v.getTag();
//        }
//        viewHolder.like_photo.setImageResource(like_people.getPhoto());
//        viewHolder.like_name.setText(like_people.getName());
//        viewHolder.like_job_title.setText(like_people.getJob_title());
//        return v;
//    }
//}
