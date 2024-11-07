package com.example.test3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Order;
import com.example.test3.R;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layoutId;//item布局
    private List<Order>orderList;
    public OrderAdapter(Context context, int layoutId, List<Order> orderList) {
        this.context = context;
        this.layoutId = layoutId;
        this.orderList = orderList;
    }
    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        TextView txt_serial=convertView.findViewById(R.id.txt_serial);
        TextView txt_allprice=convertView.findViewById(R.id.txt_allprice);
        TextView txt_time=convertView.findViewById(R.id.txt_time);
        Order order=orderList.get(position);
        txt_serial.setText(""+order.getSerial());
        txt_allprice.setText(""+order.getPrice());
        txt_time.setText(""+order.getTime());
        return convertView;
    }
}
