package com.example.test3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test3.Adapter.OrderInfoAdapter;
import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Order;
import com.example.test3.Enity.OrderInfo;
import com.example.test3.R;

import java.util.List;

public class OrderInfoActivity extends AppCompatActivity {
    private OrderInfoAdapter orderInfoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ListView act_lv_order=findViewById(R.id.act_lv_order);
        TextView txt_orderinfoprice=findViewById(R.id.txt_orderinfoprice);
        Intent intent=getIntent();
        OrderInfo orderInfo=(OrderInfo)intent.getSerializableExtra("1");
        List<Cake>cakelist=orderInfo.getCakelist();
        double price=0;
        for (Cake cake1 : cakelist) {
            price +=cake1.getNumber()* cake1.getPrice();
        }
        txt_orderinfoprice.setText("￥"+price);
        orderInfoAdapter=new OrderInfoAdapter(
                this,
                R.layout.item_orderinfo,
                cakelist
        );
        act_lv_order.setAdapter(orderInfoAdapter);

    }
    public void onResume() {
        super.onResume();
        orderInfoAdapter.notifyDataSetChanged();
    }
}