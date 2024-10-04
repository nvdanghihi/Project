package com.example.json;

import android.content.Intent;
import android.net.vcn.VcnUnderlyingNetworkTemplate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button kho,lienhe,web,hdan,dangxuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        khaibao();
        kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent khohh = new Intent(MenuActivity.this,KhohangActivity.class);
                startActivity(khohh);
            }
        });
    }

    private void khaibao() {
        kho=findViewById(R.id.btnkho);
        lienhe=findViewById(R.id.btnlhe);
        web=findViewById(R.id.btnweb);
        hdan=findViewById(R.id.btnhdan);
        dangxuat=findViewById(R.id.btndxuat);
    }
}
