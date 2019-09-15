package com.example.Activity;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class regist extends AppCompatActivity {
    private ImageView back;
    private Button Regist;
    private EditText RegistName;
    private EditText RegistPsd;
    private EditText RegistId;
    private String url = "http://192.168.100.2:8080";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regist);
        initData();
    }

    public void initData(){
        RegistName = (EditText)findViewById(R.id.MeRegistName);
        RegistPsd = (EditText)findViewById(R.id.MeRegistPsd);
        RegistId = (EditText)findViewById(R.id.MeRegistId);
        back = (ImageView)findViewById(R.id.detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist.this.finish();
            }
        });
        Regist = (Button)findViewById(R.id.MeRegist);

        String Name = RegistName.getText().toString().trim();
        String Psd = RegistPsd.getText().toString().trim();
        String Id = RegistId.getText().toString().trim();
        //构造postbody
        final OkHttpClient okHttpClient = new OkHttpClient();
        final RequestBody body = new FormBody.Builder()
                .add("Name",Name)
                .add("Id",Id)
                .add("Psd",Psd)
                .add("Subname","学生")
                .build();

        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Request request =  new Request.Builder()
                        .url(url + "/user/regist")
                        .post(body)
                        .build();

                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d("TAG", "s");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        regist.this.finish();
                    }
                });
//                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("User",Name);
//                editor.putString("Psd",Psd);
//                editor.putString("Email",Id);
//                String str="";
//                for (int i=0;i<9;i++)
//                    str+=(char)(Math.random()*26+'a');
//                editor.putString("Name",str);
//                editor.commit();
            }
        });
    }
}
