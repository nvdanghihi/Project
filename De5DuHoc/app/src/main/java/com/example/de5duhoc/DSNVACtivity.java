package com.example.de5duhoc;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DSNVACtivity extends AppCompatActivity {
    ListView nhanvien;
    ArrayList<String> ds= new ArrayList<>();
    ArrayAdapter<String> adapter;
    Button show;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvienlayout);
        khaibao();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dulieu();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void khaibao() {
        nhanvien=findViewById(R.id.listnv);
        show=findViewById(R.id.btnshow);
    }
    private void dulieu() throws JSONException{
        String json1="{" +
                "\"Ten\":\"Nguyen Van Dang\"," +
                "\"Luong\":9.000000" +
                "}";
        String json2="{" +
                "\"Ten\":\"Nguyen Van Dang\"," +
                "\"Luong\":8.000000" +
                "}";
        JSONObject jo1 = new JSONObject(json1);
        String nv1= "Tên: "+jo1.getString("Ten")+" Luong: " +jo1.getString("Luong");
        ds.add(nv1);
        adapter= new ArrayAdapter<>(DSNVACtivity.this, android.R.layout.simple_list_item_1,ds);
        nhanvien.setAdapter(adapter);
    }
}
