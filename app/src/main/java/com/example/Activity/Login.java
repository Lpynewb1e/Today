package com.example.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Activity.Bean.ActivityBean;
import com.example.Activity.Bean.UserTableBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private TextView Forget;
    private EditText psd;
    private EditText id;
    private Button LoginBotton;
    private TextView Regist;
    private ImageView back;
    private String url = "http://192.168.100.2:8080";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        Forget = findViewById(R.id.MeForget);//忘记密码
        Forget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线
        Regist = findViewById(R.id.MeRegist);//注册密码
        Regist.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        initData();
    }

    public void initData(){
        LoginBotton = (Button)findViewById(R.id.MeLoginBotton);//登录按钮
        id = (EditText) findViewById(R.id.MeId);//账号
        psd = (EditText) findViewById(R.id.MePsd);//密码
        back = (ImageView)findViewById(R.id.detail_back);//返回


        //获取用户名和密码
        final String User = id.getText().toString();
        String Password = psd.getText().toString();

        //构造postbody
        final OkHttpClient okHttpClient = new OkHttpClient();
        final RequestBody body = new FormBody.Builder()
                .add("Name",User)
                .add("Password",Password)
                .build();
        LoginBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Request request = new Request.Builder()
                        .url(url + "/user/login")
                        .post(body)
                        .build();

                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d("on Failure:","Login Wrong");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d("On Response:","Login Success");

                        Gson gson = new Gson();
                        JsonParser jsonParser = new JsonParser();
                        JsonArray jsonElements = jsonParser.parse(response.body().string()).getAsJsonArray();//获取json数组
                        UserTableBean user = gson.fromJson(jsonElements.get(0), UserTableBean.class);

                        SharedPreferences sPreferences=getBaseContext().getSharedPreferences("UserTable", getBaseContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor=sPreferences.edit();
                        //当然sharepreference会对一些特殊的字符进行转义，使得读取的时候更加准确
                        editor.putString("username", user.getName());
                        editor.putString("id", user.getId());
                        editor.putString("subname", user.getSubname());
                        editor.commit();

                        LoginBotton.isShown();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        Login.this.startActivity(intent);
                    }
                });









//                SharedPreferences sharedPreferences =  getSharedPreferences("user",MODE_PRIVATE);
//                String ids = sharedPreferences.getString("User","");
//                String psds = sharedPreferences.getString("Psd","");
//                if(psd.getText().toString().equals(psds)){
//                    Intent intent = new Intent(Login.this,MainActivity.class);
//                    Login.this.startActivity(intent);
//                }
            }
        });
        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,regist.class);
                Login.this.startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.this.finish();
            }
        });
    }
}
