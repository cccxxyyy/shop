package com.example.test3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.Enity.UserInfo;
import com.example.test3.R;
import com.example.test3.Utils.HostUtil;
import com.example.test3.Utils.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    private EditText edt_account,edt_password;
    private Button btn_register,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initEvents();
    }

    private void initEvents() {
        btn_register.setOnClickListener(v->{
            Intent intent=new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );
            startActivity(intent);
        });
        btn_login.setOnClickListener(v -> {
            new Thread(){
                @Override
                public void run() {
                    String account=edt_account.getText().toString();
                    String password=edt_password.getText().toString();
                    login(account,password);
                }
            }.start();
        });
    }

    private void login(String account, String password) {
        InputStream is=null;
        try {
            URL url=new URL(HostUtil.HOST+"login?account="+account+"&password="+password);
            is=url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String json = br.readLine();
            Gson gson=new Gson();
            Result result=gson.fromJson(json,new TypeToken<Result<UserInfo>>(){}.getType());
            if(result.getCode()!=200){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                LoginActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                        gotoMain(result);

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    private void gotoMain(Result result) {
        Intent intent=new Intent(
                LoginActivity.this,
                HomWorkActivity.class
        );
        UserInfo userInfo=(UserInfo) result.getData();
        UserInfo.ACCOUNT=userInfo.getAccount();
        UserInfo.ADDRESS=userInfo.getAddress();
        UserInfo.PASSWORD=userInfo.getPassword();
//        userInfo=(UserInfo)result.getData();
//        intent.putExtra("user",userInfo);
        startActivity(intent);
    }

    private void findViews() {
        edt_account=findViewById(R.id.edt_account);
        edt_password=findViewById(R.id.edt_password);
        btn_register=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);

    }
}