package com.example.de1kytucxaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class kitucActivity extends AppCompatActivity {
    private RadioButton r1,r2,r3,r4;
    private CheckBox cb1,cb2;
    private Button choose,exit,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kituclayout);
        khaibao();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dkiphong();
            }

            private void dkiphong() {
                showDialog();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dkicoso();
            }

            private void dkicoso() {
                Intent intent =new Intent(kitucActivity.this,CosoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        r1=findViewById(R.id.rdo1);
        r2=findViewById(R.id.rdo2);
        r3=findViewById(R.id.rdo3);
        r4=findViewById(R.id.rdo4);
        cb1=findViewById(R.id.checkBox1);
        cb2=findViewById(R.id.checkBox2);
        choose=findViewById(R.id.btnchoose);
        exit=findViewById(R.id.btnexit);
        next=findViewById(R.id.btnnext);
    }
    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin đặt phòng");
        View dialogView = getLayoutInflater().inflate(R.layout.choosedialog, null);
        builder.setView(dialogView);
        TextView typeroom=dialogView.findViewById(R.id.showtyperoom);
        TextView service1=dialogView.findViewById(R.id.showservice1);
        TextView service2=dialogView.findViewById(R.id.showservice2);
        Button thoat=dialogView.findViewById(R.id.btnthoat);
        //Show type room.
        if(r1.isChecked())
        {
            typeroom.setText(r1.getText());
        }
        else if(r2.isChecked())
        {
            typeroom.setText(r2.getText());
        }
        else if(r3.isChecked())
        {
            typeroom.setText(r3.getText());
        }
        else if(r4.isChecked())
        {
            typeroom.setText(r4.getText());
        }
        //Show service.
        if(cb1.isChecked())
        {
            service1.setText(cb1.getText());
        }

         if(cb2.isChecked()) {
            service2.setText(cb2.getText());
        }

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false); // Không cho phép thoát khi click bên ngoài
        dialog.setCancelable(false); // Không cho phép thoát khi bấm nút back trên thiết bị
        dialog.show();
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
