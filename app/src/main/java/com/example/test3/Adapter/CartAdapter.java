package com.example.test3.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Cart;
import com.example.test3.Enity.Order;
import com.example.test3.Enity.OrderInfo;
import com.example.test3.R;
import com.google.android.material.color.utilities.Cam16;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CartAdapter extends BaseAdapter {
    private Context context;//上下文环境
    private int layoutId;//item布局
    private List<Cake> cakelist;//数据源
    private HashMap mp,post;//位置与数量的映射
    private Handler handler;//更新总价格使用的handler
    public CartAdapter(Context context, int layoutId, List<Cake> cakelist,HashMap mp,HashMap post,Handler handler){
        this.context=context;
        this.layoutId=layoutId;
        this.cakelist=cakelist;
        this.mp= Cart.mp;
        this.post=Cart.post;
        this.handler=handler;
        //初始化
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
    //以上 qwq
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView) {
            convertView = LayoutInflater.from(context)
                    .inflate(layoutId, null);//第一个参数是资源，第二个是是否嵌套
        }
        //findViews(convertView);//获得控件
        TextView name,price,cnt;
        Button addtnd,subbtn,rebtn;
        ImageView img;
        img=convertView.findViewById(R.id.cake_img);
        name=convertView.findViewById(R.id.itemname_txt);
        price=convertView.findViewById(R.id.itemprice_txt );
        cnt=convertView.findViewById(R.id.cnt_txt);
        addtnd=convertView.findViewById(R.id.add_btn);
        subbtn=convertView.findViewById(R.id.sub_btn);
        rebtn=convertView.findViewById(R.id.re_btn);
        //以上控件只能这么写 每一个item的控件是单独的，不能集合
        Cake cake=cakelist.get(position);
        //获取对象
        img.setImageResource(cake.getPic());
        name.setText(cake.getName());
        price.setText("￥"+cake.getPrice().toString());
        cnt.setText(cake.getNumber()+"");
        //实现界面
        Message message = handler.obtainMessage();
        message.arg1=1;
        handler.sendMessage(message);
        //进购物车先更新一下界面
        addtnd.setOnClickListener(new View.OnClickListener() {//增加按钮
            @Override
            public void onClick(View v) {
                Integer count=cake.getNumber();//获得当前数量
                count++;//计数器加一
                cake.setNumber(count);//更新对象的cnt
                mp.put(cake.getName(),count);//更新mp 使用getNmae去获取！！！
                cnt.setText(cake.getNumber()+"");//更新ui
                Message message = handler.obtainMessage();
                message.arg1=1;
                handler.sendMessage(message);
                //通知前方更新价格
            }
        });
        subbtn.setOnClickListener(new View.OnClickListener() {//减少按钮
            @Override
            public void onClick(View v) {
                Integer count=cake.getNumber();
                int num=count.intValue();
                if(num>1){//防止出现负数和0的情况
                    count--;
                    Message message = handler.obtainMessage();
                    message.arg1=1;
                    handler.sendMessage(message);
                }
                mp.put(cake.getName(),count);//更新mp
                cake.setNumber(count);
                cnt.setText(cake.getNumber()+"");
                //刚觉放进去也行 就不放了
            }
        });
        rebtn.setOnClickListener(new View.OnClickListener() {//删除按钮
            @Override
            public void onClick(View v) {
                String name=cake.getName();
                cakelist.remove(cake);
                notifyDataSetChanged();//移除直接
                mp.put(name,null);
                post.put(name,null);//两个map更新一下
                Message message = handler.obtainMessage();
                message.arg1=1;
                handler.sendMessage(message);//更新ui
            }
        });

        return convertView;
    }
    public void up(){
        notifyDataSetChanged();
    }



}
