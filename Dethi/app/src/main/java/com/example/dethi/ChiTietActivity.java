package com.example.dethi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChiTietActivity extends AppCompatActivity {
    TextView ten,sdt;
    Button dong;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietlayout);
        khaibao();
       String tendb= getIntent().getStringExtra("Ten");
        String dt= getIntent().getStringExtra("SDT");
        ten.setText(tendb);
        sdt.setText(dt);
       dong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }

    private void khaibao() {
        ten=findViewById(R.id.textViewTen);
        sdt=findViewById(R.id.textViewSoDienThoai);
        dong=findViewById(R.id.btnDong);
    }
}

