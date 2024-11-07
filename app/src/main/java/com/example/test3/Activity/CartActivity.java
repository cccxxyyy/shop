package com.example.test3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.Adapter.CartAdapter;
import com.example.test3.Enity.AllOrder;
import com.example.test3.Enity.Cake;
import com.example.test3.Enity.Cart;
import com.example.test3.Enity.Order;
import com.example.test3.Enity.OrderInfo;
import com.example.test3.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
最难的一集来了
 */
public class CartActivity extends AppCompatActivity {

    private List<Cake>cakelist;//商品列表
    private TextView allprice;//总价格
    private HashMap mp,post;//两个map
    private ListView listView;//界面的lv
    private Button btn_buy;
    private Handler handler;//传数据的handler，保证Adapter更新后主界面的ui更新
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        handler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                work();
            }
        };
        //使用handler去获取消息队列中的数据，只要更新了就去更新ui的总价格
        findViews();//获取控件
        init(Cart.goods,Cart.mp,Cart.post);//初始化
        Intent intent=getIntent();
        Cake cake=(Cake)intent.getSerializableExtra("1");
        String name=cake.getName();
        /*获取当前对象的名称，主要是因为每次加入购物车的商品不被认定为同一个对象
        只能使用map来操作 防止重复进入
        */
        if ( mp.get(name)==null) {

            cakelist.add(cake);//加入list
            post.put(name, cakelist.indexOf(cake));//第一次顺带存位置
            mp.put(name, 1);//如果是第一次进入，置成1
            cake.setNumber(1);//保持一致
//cakelist:2 3 4 5
        } else {
            Integer cnt = (Integer) mp.get(name);
            cnt++;
            mp.put(name, cnt);//之后再进来就计数器加一即可
            Cake cake1 = cakelist.get((Integer) post.get(name));//获取上次进来的位置的对象
            cake1.setNumber(cnt);//将商品的数量保持和map一致
        }

        CartAdapter cartAdapter=new CartAdapter(
                this,
                R.layout.item_cart,
                cakelist,
                mp,
                post,
                handler
        );//传进去必要的参数
        listView.setAdapter(cartAdapter);//绑定
        btn_buy.setOnClickListener(v -> {
            buy();
            cartAdapter.up();
        });
    }

    private void work() {
        double price = 0;
        for (Cake cake1 : cakelist) {
           price +=cake1.getNumber()* cake1.getPrice();
        }
        allprice.setText("￥"+price);
        //循环解决问题，想用个复杂度好一点的方法，放弃了
    }
    private void findViews() {
        listView=findViewById(R.id.lv_goods);
        allprice=findViewById(R.id.allprice_txt);
        btn_buy=findViewById(R.id.btn_buy);
    }
    private void init(List<Cake> cakelist, HashMap mp, HashMap post) {
        this.cakelist = cakelist;
        this.mp = mp;
        this.post = post;
    }
    private void buy(){
        Order order = new Order();
        OrderInfo orderInfo=new OrderInfo();

        List<Cake>newcakelist=new ArrayList<>();
        for (Cake cake1 : cakelist) {
            newcakelist.add(cake1);
        }
        orderInfo.setCakelist(newcakelist);
        Order.orderInfoList.add(orderInfo);
        Date date=new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        order.setTime(ft.format(date));
        double price = 0;
        for (Cake cake1 : cakelist) {
            price +=cake1.getNumber()* cake1.getPrice();
        }
        order.setPrice(price);
        order.setSerial(getRandomString(10));
        AllOrder.allolderlist.add(order);
        cakelist.clear();
        mp.clear();
        post.clear();
        Message message = handler.obtainMessage();
        message.arg1=1;
        handler.sendMessage(message);//更新ui


    }
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}