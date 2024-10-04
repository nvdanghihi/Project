package com.example.de1kytucxaactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentInforActivity extends AppCompatActivity {

    private ListView listViewSinhVien;
    private Button buttonBack;
    private Button buttonShow;

    private String jsonData = "[{\"ten\":\"Nguyen Van A\",\"gpa\":\"3.5\"},{\"ten\":\"Tran Thi B\",\"gpa\":\"3.8\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ifor_layout);

        listViewSinhVien = findViewById(R.id.listinfor);
        buttonBack = findViewById(R.id.btnback);
        buttonShow = findViewById(R.id.btnshow);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> sinhVienList = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ten = jsonObject.getString("ten");
                        String gpa = jsonObject.getString("gpa");
                        sinhVienList.add(ten + " - GPA: " + gpa);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(StudentInforActivity.this, android.R.layout.simple_list_item_1, sinhVienList);
                listViewSinhVien.setAdapter(adapter);
            }
        });
    }
}




Show.setOnClickListener(new View.OnClickListener() {
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

    private void dulieu() throws JSONException {
        String json1 = "{" +
                "\"name\":\"Anh tuan\"," +
                "\"luong\":1.000.000" +
                "}";
        String json2 = "{" +
                "\"name\":\"Anh hi\"," +
                "\"luong\":11.000.000" +
                "}";
        JSONObject jb1 = new JSONObject(json1);
        String nv1 = "Name: " + jb1.getString("name") + "\nLuong: " + jb1.getString("luong");
        JSONObject jb2 = new JSONObject(json2);
        String nv2 = "Name: " + jb2.getString("name") + "\nLuong: " + jb2.getString("luong");
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ds);
        ds.add(nv1);
        ds.add(nv2);
        listds.setAdapter(adapter);
    }
