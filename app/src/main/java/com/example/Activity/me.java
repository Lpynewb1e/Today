package com.example.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class me extends Fragment {
    private TextView id;
    private RelativeLayout take_RelativeLayout;
    private Button me_login;
    private TextView name;
    private RelativeLayout relativeLayout;
    private ImageView me_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = (TextView)getActivity().findViewById(R.id.me_name);//昵称
        me_photo = (ImageView)getActivity().findViewById(R.id.me_photo);//头像
        id = (TextView)getActivity().findViewById(R.id.me_id);//id
        me_login = (Button) getActivity().findViewById(R.id.me_login);//登录按钮
        relativeLayout = (RelativeLayout)getActivity().findViewById(R.id.me_relative);
        take_RelativeLayout = (RelativeLayout) getActivity().findViewById(R.id.w);
        take_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),me_take.class);
                getActivity().startActivity(intent);
            }
        });

        me_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().startActivity(intent);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap bitmap = ((BitmapDrawable)me_photo.getDrawable()).getBitmap();
//                bitmap = Bitmap.createBitmap(bitmap);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
//                byte[] b = baos.toByteArray();
//                Toast.makeText(getActivity(),"sada",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),me_detail.class);
//                intent.putExtra("me_photo",b);
                getActivity().startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String names = sharedPreferences.getString("Name","");
        String ids =sharedPreferences.getString("User","");
//        String base4 = sharedPreferences.getString("photo","");
//        Bitmap bitmap =null;
//        if(base4!=null){
//            byte[] b = Base64.decode(base4.getBytes(),1);
//            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
//            me_photo.setImageBitmap(bitmap);
//        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Bitmap bitmap = ((BitmapDrawable)me_photo.getDrawable()).getBitmap();
        bitmap = Bitmap.createBitmap(bitmap);
        ByteArrayOutputStream bacs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bacs);
        String base4 = new String(Base64.encodeToString(bacs.toByteArray(),Base64.DEFAULT));
        editor.putString("photo",base4);
        editor.commit();
        name.setText(names);
        id.setText(ids);
    }
}
