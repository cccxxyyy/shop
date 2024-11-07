package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test3.Enity.Cake;
import com.example.test3.Enity.UserInfo;
import com.example.test3.Fragment.Fragment_Mine;
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

public class ChangeActivity extends AppCompatActivity {

    private EditText edt_changepassword;
    private EditText edt_changeaddress;
    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        findview();
        Intent intent=getIntent();
        UserInfo userInfo=(UserInfo)intent.getSerializableExtra("user");
        edt_changeaddress.setText(userInfo.getAddress());
        btn_change.setOnClickListener(v -> {
            String address=edt_changeaddress.getText().toString();
            String password=edt_changepassword.getText().toString();
            new Thread(){
                @Override
                public void run() {
                    change(userInfo.getAccount(),address,password,intent);
                }
            }.start();
        });
    }

    private void change(String account,String address, String password,Intent intent) {
        InputStream is=null;
        try {
            URL url=new URL(HostUtil.HOST+"change?account="+account+"&address="+address+"&password="+password);
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
                                ChangeActivity.this,
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
                                ChangeActivity.this,
                                result.getMsg(),
                                Toast.LENGTH_LONG
                        ).show();
                        UserInfo.ADDRESS=edt_changeaddress.getText().toString();
                        UserInfo.PASSWORD=edt_changepassword.getText().toString();
                        Intent back_intent = new Intent();
                        intent.setClass(ChangeActivity.this, Fragment_Mine.class);
                        setResult(1, back_intent);
                        ChangeActivity.this.finish();

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

    private void gotoMine() {
        Intent intent=new Intent(
                ChangeActivity.this,
                HomWorkActivity.class
        );
        startActivity(intent);
    }

    private void findview() {
        edt_changepassword = findViewById(R.id.edt_changepassword);
        edt_changeaddress = findViewById(R.id.edt_changeaddress);
        btn_change = findViewById(R.id.btn_change);
    }
}