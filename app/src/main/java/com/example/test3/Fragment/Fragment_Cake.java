package com.example.test3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.test3.Adapter.CakeAdapter;
import com.example.test3.Enity.Cake;
import com.example.test3.R;
import com.example.test3.Utils.HostUtil;
import com.example.test3.Utils.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Cake extends Fragment implements Fragment_work{
    public List<Cake>cakelist=new ArrayList<>();;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_shop, null);
        GridView gv_recommend = view.findViewById(R.id.shop_gv);
        new Thread(){
            @Override
            public void run() {
                getcake();
            }
        }.start();
        while(cakelist.size()==0){

        }
        CakeAdapter recommendAdapter = new CakeAdapter(
                this.getContext(),
                R.layout.item_cake,
                cakelist
        );
        gv_recommend.setAdapter(recommendAdapter);
        FragmentActivity activity = getActivity();
        work(gv_recommend,activity,cakelist);

        return gv_recommend;
    }
    private void getcake() {
        InputStream is=null;
        try {
            URL url=new URL(HostUtil.HOST+"getcake");
            is=url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String json = "";
            String str = null;
            while((str = br.readLine()) != null){
                json += str;
            }
            Gson gson=new Gson();
            Result result =gson.fromJson(json,new TypeToken<Result<List<Cake>>>() {}.getType());
            cakelist= (List<Cake>) result.getData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}