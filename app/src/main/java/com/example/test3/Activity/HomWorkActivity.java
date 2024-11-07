package com.example.test3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.test3.Adapter.FragmentShopAdapter;
import com.example.test3.Enity.Cake;
import com.example.test3.Enity.UserInfo;
import com.example.test3.Fragment.Fragment_Mine;
import com.example.test3.Fragment.Fargment_Order;
import com.example.test3.Fragment.Fragment_Cake;
import com.example.test3.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomWorkActivity extends AppCompatActivity {
    private TabLayout tb_shop;
    private ViewPager2 vp_shop;//主页面的两个控件
    private List<String> tabNameList;//游标的list
    private List<Fragment> fragmentList;//fragment的list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_hom_work);
        findViews();//获取控件

        initFragment();//初始化Fragment

        FragmentShopAdapter shopAdapter = new FragmentShopAdapter(
                fragmentList,//数据源
                this//当前的activity
        );
        vp_shop.setAdapter(shopAdapter);//给ViewPager2绑定适配器

        TabLayoutMediator mediator = new TabLayoutMediator(
                tb_shop,
                vp_shop,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabNameList.get(position));
                    }
                }
        );
        mediator.attach();
        // 这里使用匿名内部类的方式去关联，按照下标的方式依次绑定标签和Fragment
        //以上是基础的三个界面，点击事件我们要前往Fragment里，下一步前往Fragment


    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_Cake());
        fragmentList.add(new Fargment_Order());
        fragmentList.add(new Fragment_Mine());


        tabNameList = new ArrayList<>();
        tabNameList.add("推荐");
        tabNameList.add("订单");
        tabNameList.add("我的");
    }

    private void findViews() {
        tb_shop = findViewById(R.id.tb_shop);
        vp_shop = findViewById(R.id.vp_shop);
    }


}