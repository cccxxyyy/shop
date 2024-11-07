package com.example.test3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Order;
import com.example.test3.Enity.OrderInfo;
import com.example.test3.R;

import java.util.List;

public class OrderInfoAdapter extends BaseAdapter {
    private Context context;
    private int layoutId;//item布局
    private List<Cake>cakeList;
    public OrderInfoAdapter(Context context, int layoutId, List<Cake> cakeList) {
        this.context = context;
        this.layoutId = layoutId;
        this.cakeList = cakeList;
    }
    @Override
    public int getCount() {
        return cakeList.size();
    }

    @Override
    public Object getItem(int position) {
        return cakeList.get(position);
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
        ImageView order_cake_img=convertView.findViewById(R.id.order_cake_img);
        TextView order_itemname_txt=convertView.findViewById(R.id.order_itemname_txt);
        TextView order_itemnumber_txt=convertView.findViewById(R.id.order_itemnumber_txt);
        TextView order_itemprice_txt=convertView.findViewById(R.id.order_itemprice_txt);
        Cake cake=cakeList.get(position);
        order_cake_img.setImageResource(cake.getPic());
        order_itemname_txt.setText(cake.getName());
        order_itemnumber_txt.setText("x"+cake.getNumber().toString());
        order_itemprice_txt.setText("￥"+cake.getPrice().toString());


        return convertView;
    }
}
