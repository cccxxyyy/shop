package com.example.test3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test3.Activity.OrderInfoActivity;
import com.example.test3.Adapter.OrderAdapter;
import com.example.test3.Enity.AllOrder;
import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Order;
import com.example.test3.Enity.OrderInfo;
import com.example.test3.R;

import java.util.ArrayList;
import java.util.List;

public class Fargment_Order extends Fragment {
    private OrderAdapter orderAdapter;
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
        View view = inflater.inflate(R.layout.item_order, null);//加载布局文件
        ListView lv_order=view.findViewById(R.id.lv_order);
        List<Order> orderList= AllOrder.allolderlist;
        orderAdapter=new OrderAdapter(
                this.getContext(),
                R.layout.item_order_adapter,
                orderList
        );
        lv_order.setAdapter(orderAdapter);
        lv_order.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent=new Intent();
            intent.setClass(
                    getActivity(),
                    OrderInfoActivity.class
            );
            OrderInfo orderInfo=Order.orderInfoList.get(position);
            intent.putExtra("1", orderInfo);
            startActivity(intent);

        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        orderAdapter.notifyDataSetChanged();
    }
}