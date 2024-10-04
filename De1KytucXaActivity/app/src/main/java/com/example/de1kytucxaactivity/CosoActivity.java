package com.example.de1kytucxaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CosoActivity extends AppCompatActivity {
    private ListView lvcs;
    private Button next;
    private ArrayList<String> coso;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosolayout);
        khaibao();
        lvcs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                String selectCoso=(String) coso.get(positon);
                String message = "Bạn đã chọn cơ sở: " + selectCoso ;
                Toast.makeText(CosoActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CosoActivity.this,StudentInforActivity.class);
                startActivity(intent);
            }
        });

    }

    private void khaibao() {
        lvcs=findViewById(R.id.listcoso);
        next=findViewById(R.id.btnnextcs);
        coso = new ArrayList<>();
        coso.add("Lĩnh nam");
        coso.add("Minh Khai");
        coso.add("Nam Định");
        coso.add("Mỹ Xá");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,coso);
        lvcs.setAdapter(adapter);
    }
}
