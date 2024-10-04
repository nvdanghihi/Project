package com.example.de5duhoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TuVanDuhocACtivity extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    RadioGroup radio;
    CheckBox ck1,ck2;
    Button choose,Exit,next;
    Context context;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuvanlayout);
        khaibao();
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dkiduhoc();
            }

            private void dkiduhoc() {
                showdialog();
            }

        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TuVanDuhocACtivity.this,QuocgiaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        r1=findViewById(R.id.rdo1);
        r2=findViewById(R.id.rdo2);
        r3=findViewById(R.id.rdo3);
        r4=findViewById(R.id.rdo4);
        ck1=findViewById(R.id.cbo1);
        ck2=findViewById(R.id.cbo2);
        choose=findViewById(R.id.btnchoose);
        Exit=findViewById(R.id.btnExit);
        next=findViewById(R.id.btnnext);
    }
    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin du học");
        View view = getLayoutInflater().inflate(R.layout.diaglog_layout,null);
        builder.setView(view);
        TextView nv=view.findViewById(R.id.shownv);
        TextView ht1 = view.findViewById(R.id.showht2);
        TextView ht2 = view.findViewById(R.id.showht2);

        if(r1.isChecked())
        {
            nv.setText(r1.getText());
        }
        if(ck1.isChecked())
        {
            ht1.setText(ck1.getText());
        }
        final AlertDialog dialog = builder.create();
        dialog.show();
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
