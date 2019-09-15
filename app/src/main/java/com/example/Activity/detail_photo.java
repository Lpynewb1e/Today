package com.example.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class detail_photo extends AppCompatActivity {
    private ImageView photo;
    private ImageView back;
    private ImageView shenglue;
    private PopupWindow popupWindow;
    private View view;
    private SharedPreferences sharedPreferences;
    private String filename;
    private File photofile = null;
    private Uri temuri = null;

    private static final String PATH = "/sdcard/privateSpace/photos";

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceStata){
        super.onCreate(saveInstanceStata);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_photo);
        initData();
    }

    public void initData(){
        photo = (ImageView)findViewById(R.id.big_photo);//头像详情
        back = (ImageView)findViewById(R.id.detail_back);//返回
        shenglue = (ImageView)findViewById(R.id.me_modify_photo_button);//保存、换照片
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
//        bitmap = Bitmap.createBitmap(bitmap);
//        ByteArrayOutputStream bacs = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,bacs);
//        String base4 = new String(Base64.encodeToString(bacs.toByteArray(),Base64.DEFAULT));
//        editor.putString("photo",base4);
//        editor.commit();
        String base4 = sharedPreferences.getString("photo","");
        Bitmap bitmap =null;
        if(base4!=null){
            byte[] b = Base64.decode(base4.getBytes(),1);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
            photo.setImageBitmap(bitmap);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                detail_photo.this.finish();
            }
        });
        ShowPopupWindow();
        shenglue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
            }
        });
        view.findViewById(R.id.cencel_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //从相机拍照
        view.findViewById(R.id.take_photos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date(System.currentTimeMillis());
                filename = sim.format(date);
                photofile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename + ".jpg");
                try{
                    if (!photofile.getParentFile().exists())
                        photofile.getParentFile().mkdirs();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT < 24)
                    temuri = Uri.fromFile(photofile);
                else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, photofile.getAbsolutePath());
//                temuri = FileProvider.getUriForFile(view.getContext(), "com.example.TodayActivity.fileprovider",photofile);
                    temuri = getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                System.out.println(photofile);
                System.out.println(temuri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,temuri);
                startActivityForResult(intent,1);
                popupWindow.dismiss();
            }
        });
        //相册选取照片
        view.findViewById(R.id.photo_from_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                OpenAlbumIntent.setType("image/*");
                startActivityForResult(OpenAlbumIntent,1);
                popupWindow.dismiss();
            }
        });
    }

    //悬浮窗口
    public void ShowPopupWindow(){
        view = View.inflate(this,R.layout.photo_popupwindow,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        ColorDrawable dw = new ColorDrawable(0x30000000);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置半透明
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setAnimationStyle(R.style.popupWindow_anim_style);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                //裁剪照片
                case 1:
                    System.out.println("aaaasssssss");
                    CutImage(Uri.fromFile(photofile));
                    break;

                //显示照片
                case 3:
                    if(data != null)
                        setImageToView(data);
                    break;
            }
        }
    }

    protected void CutImage(Uri uri){
        if (uri == null){
            Toast.makeText(view.getContext(),"the uri is not exist.",Toast.LENGTH_SHORT).show();;
        }
        temuri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(temuri,"image/*");
        //设置裁剪
        intent.putExtra("crop",true);
        //aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //outputX putputY 是图片的宽高
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,3);
    }

    protected void setImageToView(Intent data){
        Bundle extras =data.getExtras();
        if (extras != null){
            Bitmap mBitMap = extras.getParcelable("data");
            photo.setImageBitmap(mBitMap);
            //上传到服务器
            //
            //
        }
    }


}
