package com.example.test3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test3.Activity.ChangeActivity;
import com.example.test3.Enity.UserInfo;
import com.example.test3.R;

public class Fragment_Mine extends Fragment {
    private TextView txt_account;
    private TextView txt_address;
    private Button btn_change;
    private ActivityResultLauncher<Intent>launcher;


    /*
        三个Fragment内容差不多 代码重复度有点高 但这也是简化过的版本了
        优化了如下几点
        1.整体的布局文件采用了同一个xml，简化了xml的数目
        2.使用继承和多态的属性，用Goods商品大类来进行后续操作，不过我觉得还能简化 只是我不会写
        3.用同一个Adapter 使用Goods类去操作，简化Adapter数目，上次写的是三个Adapter
        4.最会使用java的一集，使用了一个接口去完成点击监听器，让每一个Fragment简化了代码，最有意义的一个接口
         */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_mine, null);//加载布局文件
        findview(view);

        txt_account.setText("账号："+UserInfo.ACCOUNT);
        if(UserInfo.ADDRESS==null||UserInfo.ADDRESS.equals("")){
            txt_address.setText("地址：无");
        }else{
            txt_address.setText("地址："+UserInfo.ADDRESS);
        }
        launcher= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        switch (result.getResultCode()) { //resultCode在跳转Activity设置
                            case 1:
                                Intent intent = result.getData();
                                txt_address.setText("地址："+UserInfo.ADDRESS);
                                break;
                        }
                    }
                });
        btn_change.setOnClickListener(v -> {
            Intent intent=new Intent(
                    getActivity(),
                    ChangeActivity.class
            );
            UserInfo userInfo=new UserInfo();
            userInfo.setPassword(UserInfo.PASSWORD);
            userInfo.setAddress(UserInfo.ADDRESS);
            userInfo.setAccount(UserInfo.ACCOUNT);
            intent.putExtra("user",userInfo);
            launcher.launch(intent);
        });


        return view;
    }



    private void findview(View view) {
        txt_account= view.findViewById(R.id.txt_account);
        txt_address = view.findViewById(R.id.txt_address);
        btn_change = view.findViewById(R.id.btn_change);
    }


}