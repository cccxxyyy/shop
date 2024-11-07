package com.example.test3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test3.Enity.Cake;
import com.example.test3.R;

import java.util.List;

public class CakeAdapter extends BaseAdapter {
    private Context context;//上下文环境
    private int layoutId;//item布局
    private List<Cake> cakelist;//数据源

    public CakeAdapter(Context context, int layoutId, List<Cake> cakelist) {
        this.context = context;
        this.layoutId = layoutId;
        this.cakelist = cakelist;
    }
    @Override
    public int getCount() {
        return cakelist.size();
    }

    @Override
    public Object getItem(int position) {
        return cakelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //很简单的三个方法，伟大无需多言

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }//获取当前的view，没有就创建
        ImageView iv_pic = convertView.findViewById(R.id.iv_pic);
        TextView tv_count = convertView.findViewById(R.id.tv_count);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_price = convertView.findViewById(R.id.tv_price);
        //获取控件
        Cake cake = cakelist.get(position);
        iv_pic.setImageResource(cake.getPic());
        tv_count.setText("仅剩下"+cake.getCount().toString()+"个");
        tv_name.setText(cake.getName().toString());
        tv_price.setText("￥"+cake.getPrice());
        //填充内容 很简单的Adapter写法
        return convertView;
    }
}
