package com.example.test3.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.FragmentActivity;

import com.example.test3.Activity.ShopPageActivity;
import com.example.test3.Enity.Cake;

import java.util.List;
public interface Fragment_work {
    /*
    这个接口用来实现Fragment的item监听器，我们只需要把activity传进来之后
    正常创建监听器即可
     */
    default void work(GridView view,FragmentActivity activity,List<Cake>cakesList){
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(
                        activity,
                        ShopPageActivity.class
                );
                intent.putExtra("1",cakesList.get(position));
                activity.startActivity(intent);
            }
        });
    }
}
