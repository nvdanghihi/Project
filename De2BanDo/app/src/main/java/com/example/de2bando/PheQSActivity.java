package com.example.de2bando;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PheQSActivity extends AppCompatActivity {
    private ListView dsphe;
    private ArrayAdapter<String> adapter;
    private ArrayList <String> phe;
    private Button ok;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pheqs_layout);
        khaibao();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent list =new Intent(PheQSActivity.this,DSnguoichoiActivity.class);
                startActivity(list);
            }
        });
        dsphe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectPhe=(String) phe.get(i);
                String message = "Bạn đã chọn phe: " + selectPhe ;
                Toast.makeText(PheQSActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void khaibao() {
        dsphe=findViewById(R.id.listpheqs);
        ok=findViewById(R.id.btnok);
        phe=new ArrayList<>();
        phe.add("Roman");
        phe.add("Pesian");
        phe.add("Shangg");
        phe.add("Greek");
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,phe);
        dsphe.setAdapter(adapter);
    }
}
