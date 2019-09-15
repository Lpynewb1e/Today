package com.example.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class me_detail extends AppCompatActivity {
    private TextView Name;
    private TextView Id;
    private ImageView back;
    private RelativeLayout NameRelativeLayout;
    private ImageView photo;
    private RelativeLayout PhotoRelativeLayout;
    private TextView SubName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_people_detaile);
        initData();
    }

    public void initData(){
        Name = (TextView)findViewById(R.id.MeName);//昵称
        Id = (TextView)findViewById(R.id.MeId);//id
        back = (ImageView)findViewById(R.id.detail_back);//返回
        SubName = (TextView)findViewById(R.id.MeSubname);
        NameRelativeLayout = (RelativeLayout)findViewById(R.id.MeNameRelativeLayout) ;
        PhotoRelativeLayout = (RelativeLayout)findViewById(R.id.MePhotoRelativeLayout);
        photo = (ImageView)findViewById(R.id.MePhoto);
        SharedPreferences sharedPreferences = getSharedPreferences("UserTable",MODE_PRIVATE);
        String base4 = sharedPreferences.getString("photo","");
        String names = sharedPreferences.getString("username","");
        String ids = sharedPreferences.getString("id","");
        String subname = sharedPreferences.getString("subname","");
        SubName.setText(subname);
        Name.setText(names);
        Id.setText(ids);
        Bitmap bitmap =null;
        if(base4!=null){
            byte[] b = Base64.decode(base4.getBytes(),1);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
            photo.setImageBitmap(bitmap);
        }
        PhotoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me_detail.this,detail_photo.class);
                Bitmap bitmap1 = ((BitmapDrawable)photo.getDrawable()).getBitmap();
                bitmap1 = Bitmap.createBitmap(bitmap1);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] c = baos.toByteArray();
                intent.putExtra("photo",c);
                me_detail.this.startActivity(intent);
            }
        });
        NameRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me_detail.this,modify_name.class);
                me_detail.this.startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(me_detail.this,MainActivity.class);
                me_detail.this.startActivity(intent);
            }
        });
    }
}
