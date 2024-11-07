package com.example.test3.Enity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;//序列化 方便后续Intent的使用

    public List<Cake> getCakelist() {
        return cakelist;
    }

    public void setCakelist(List<Cake> cakelist) {
        this.cakelist = cakelist;
    }
    private List<Cake>cakelist=new ArrayList<>();


}
