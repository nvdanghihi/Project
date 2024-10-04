package com.example.de5duhoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuocgiaActivity extends AppCompatActivity {
    ListView listqg;
    ArrayAdapter<String> adapter;
    ArrayList<String> qgia;
    Button Next;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quocgia_layout);
        khaibao();
        listqg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectqg;
                selectqg=qgia.get(i);
                String mesage="Bạn đã chọn quốc gia: "+selectqg;
                Toast.makeText(QuocgiaActivity.this,mesage,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void khaibao() {
        Next=findViewById(R.id.btnNext);
        listqg=findViewById(R.id.listqg);
        qgia= new ArrayList<>();
        qgia.add("Anh");
        qgia.add("Đức");
        adapter= new ArrayAdapter<>(QuocgiaActivity.this, android.R.layout.simple_list_item_1,qgia);
        listqg.setAdapter(adapter);

    }
}
