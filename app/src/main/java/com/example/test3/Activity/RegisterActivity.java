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

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_account;
    private EditText edt_password;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        initEvents();
    }

    private void initEvents() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=edt_account.getText().toString();
                String password=edt_password.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        register(account,password);
                    }
                }.start();
            }
        });
    }

    private void register(String account, String password) {
        InputStream is=null;
        try {
            URL url=new URL(HostUtil.HOST+"register?account="+account+"&password="+password);
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
                                RegisterActivity.this,
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
                                RegisterActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                        gotoLogin();

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

    private void findViews() {
        edt_account = findViewById(R.id.edt_account);
        edt_password = findViewById(R.id.edt_password);
        btn_register=findViewById(R.id.btn_register);
    }
    private void gotoLogin(){
        Intent intent=new Intent(
                RegisterActivity.this,
                LoginActivity.class
        );
        startActivity(intent);
    }
}