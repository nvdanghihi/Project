package com.example.dethi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class ThongtinActivity extends AppCompatActivity {
    EditText ten,sdt;
    ArrayList<DanhBa> danhba;
    ArrayAdapter<DanhBa> adapter;
    ListView ds;
    Button them,sua,xoa,huy,chitiet;
    int selectpos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhbalayout);
        khaibao();
        ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectpos=i;
                String message="Chọn: "+danhba.get(i).getTen();
                Toast.makeText(ThongtinActivity.this, message, Toast.LENGTH_SHORT).show();
                ten.setText(danhba.get(i).getTen());
                sdt.setText(danhba.get(i).getSdt());
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhba.add(new DanhBa(ten.getText().toString(),sdt.getText().toString()));
                adapter= new ArrayAdapter<>(ThongtinActivity.this, android.R.layout.simple_list_item_1,danhba);
                ds.setAdapter(adapter);
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newten=danhba.get(selectpos).getTen();
                String newdt= danhba.get(selectpos).getSdt();
                danhba.get(selectpos).setTen(newten);
                danhba.get(selectpos).setSdt(newdt);
                selectpos=-1;
                adapter= new ArrayAdapter<>(ThongtinActivity.this, android.R.layout.simple_list_item_1,danhba);
                ds.setAdapter(adapter);
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectpos!=-1)
                {
                    danhba.remove(selectpos);
                    selectpos=-1;
                    adapter= new ArrayAdapter<>(ThongtinActivity.this, android.R.layout.simple_list_item_1,danhba);
                    ds.setAdapter(adapter);
                }
            }
        });
        chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectpos!=-1)
                {
                    Intent intent = new Intent(ThongtinActivity.this,ChiTietActivity.class);
                    intent.putExtra("Ten",danhba.get(selectpos).getTen());
                    intent.putExtra("SDT",danhba.get(selectpos).getSdt());
                    startActivity(intent);
                }
            }
        });
    }

    private void khaibao() {
        ten=findViewById(R.id.txtname);
        sdt=findViewById(R.id.txtsdt);
        ds=findViewById(R.id.listViewDanhBa);
        them=findViewById(R.id.btnThem);
        chitiet=findViewById(R.id.btnChiTiet);
        xoa=findViewById(R.id.btnXoa);
        sua=findViewById(R.id.btnsua);
        huy=findViewById(R.id.btnHuy);
        danhba= new ArrayList<>();
        danhba.add(new DanhBa("Dang","09862555"));
        danhba.add(new DanhBa("Anh","09862555"));
        danhba.add(new DanhBa("Cuong","09862555"));
        adapter= new ArrayAdapter<>(ThongtinActivity.this, android.R.layout.simple_list_item_1,danhba);
        ds.setAdapter(adapter);
    }
}

