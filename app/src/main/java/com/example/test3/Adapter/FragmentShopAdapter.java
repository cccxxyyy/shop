package com.example.test3.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentShopAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public FragmentShopAdapter(List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }//改变一下参数先，传进来一个数据源中的list

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }//返回当前位置的Fragment

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
    //获取当前list的大小
}