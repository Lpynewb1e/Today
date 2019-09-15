//package com.example.TodayActivity;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class commit_adapter extends BaseAdapter {
//    private ArrayList<commit_people> list;
//    private Context context;
//
//    public commit_adapter(Context context,ArrayList<commit_people> list){
//        this.context = context;
//        this.list = list;
//    }
//
//    public class ViewHolder{
//        ImageView commit_photo;
//        TextView commit_name;
//        TextView commot_job_title;
//        ImageView commit_like;
//        TextView commit_content;
//        TextView commit_time;
//        TextView commit_reply;
//        TextView commit_number;
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
//        final ViewHolder viewHolder;
//        View v;
//        final commit_people commit_people = list.get(position);
//        if(convertView == null){
//            v = View.inflate(context,R.layout.commit_people,null);
//            viewHolder = new ViewHolder();
//            viewHolder.commit_photo = (ImageView)v.findViewById(R.id.commit_photo);
//            viewHolder.commit_name = (TextView)v.findViewById(R.id.commit_name);
//            viewHolder.commot_job_title = (TextView)v.findViewById(R.id.commit_job_title);
//            viewHolder.commit_like = (ImageView)v.findViewById(R.id.commit_like);
//            viewHolder.commit_number = (TextView) v.findViewById(R.id.commit_like_number);
//            viewHolder.commit_content = (TextView)v.findViewById(R.id.commit_content);
//            viewHolder.commit_time = (TextView)v.findViewById(R.id.commit_time);
//            viewHolder.commit_reply = (TextView)v.findViewById(R.id.commit_reply);
//            v.setTag(viewHolder);
//        }else {
//            v = convertView;
//            viewHolder = (ViewHolder) v.getTag();
//        }
//        viewHolder.commit_photo.setImageResource(commit_people.getCommit_photo());
//        viewHolder.commit_name.setText(commit_people.getCommit_name());
//        viewHolder.commot_job_title.setText(commit_people.getCommot_job_title());
//        viewHolder.commit_like.setImageResource(commit_people.getCommit_like());
//        viewHolder.commit_number.setText(commit_people.getCommit_number());
//        viewHolder.commit_content.setText(commit_people.getCommit_content());
//        viewHolder.commit_time.setText(commit_people.getCommit_time());
//        viewHolder.commit_reply.setText(commit_people.getCommit_reply());
//        viewHolder.commit_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(viewHolder.commit_number.getText().toString().trim());
//                String str;
//                str = Integer.toString(count+1);
//                commit_people.setCommit_number(str);
//                viewHolder.commit_number.setText(commit_people.getCommit_number());
//            }
//        });
//        return v;
//    }
//}
