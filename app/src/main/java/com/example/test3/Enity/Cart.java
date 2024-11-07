package com.example.test3.Enity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
购物车类也是本作业的核心之一，也是采用lzh的建议来使用静态的一些成员完成后续操作，刘神 伟大
使用静态成员的原因是，每次加入购物车的时候都是再次调用(实例化？不太清楚)了CartActivity类，里面的一些数组 map等都会变成新的
使用一个Cart类去表示也会更加方便一点
 */
public class Cart {

   public static List<Cake>goods=new ArrayList<>();//购物车的商品列表
   public static HashMap<String,Integer>mp=new HashMap<String, Integer>();//数量的map
   public static HashMap<String,Integer>post=new HashMap<String, Integer>();//位置的map
   //插一嘴 如果是c++的stl其实用pair就能直接解决了 都不用开俩 可惜我不会java（悲

}
