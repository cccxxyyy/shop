package com.example.test3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test3.Enity.Cake;

import com.example.test3.R;
/*
详细页面这里，为了方便，我们只写了一个很简单的界面
 */
public class ShopPageActivity extends AppCompatActivity {
    private TextView pricestxt,nametxt,count;
    private Button carbtn;
    private ImageView goodpic;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);
        findViews();//获得控件
        Intent intent=getIntent();
        Cake cake= (Cake)intent.getSerializableExtra("1");//获取intent
        pricestxt.setText("券后只需￥"+cake.getPrice());
        nametxt.setText(cake.getName());
        count.setText("当前只剩下"+cake.getCount().toString()+"个 手慢无！");
        goodpic.setImageResource(cake.getPic());
        //以上实现布局
        carbtn.setOnClickListener(v -> {
            Intent intent1 =new Intent();
            intent1.setClass(
                   ShopPageActivity.this,
                    CartActivity.class
            );

            intent1.putExtra("1",cake);
            startActivity(intent1);
        });
        //注册按钮监听器，再使用Intent跳转到购物车页面，记得实现Goods父类的序列化操作

    }

    private void findViews() {
        pricestxt=findViewById(R.id.prices_txt);
        nametxt=findViewById(R.id.name_txt);
        count=findViewById(R.id.count_txt);
        carbtn=findViewById(R.id.car_btn);
        goodpic=findViewById(R.id.good_pic);
    }
}