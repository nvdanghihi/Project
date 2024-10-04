package com.example.de2bando;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BandoActivity extends AppCompatActivity {
    private RadioButton rdo1, rdo2, rdo3, rdo4;
    private CheckBox cb1, cb2;
    private Button choose, exit, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bando_layout);
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
                Showtt();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dsphe=new Intent(BandoActivity.this,PheQSActivity.class);
                startActivity(dsphe);
            }
        });
    }

    private void khaibao() {
        rdo1 = findViewById(R.id.rdocn);
        rdo2 = findViewById(R.id.rdohd);
        rdo3 = findViewById(R.id.rdodb);
        rdo4 = findViewById(R.id.rdobb);
        cb1 = findViewById(R.id.cbo1); // Thêm khai báo cho checkbox1
        cb2 = findViewById(R.id.cbo2); // Thêm khai báo cho checkbox2
        choose = findViewById(R.id.btnchoose);
        exit = findViewById(R.id.btnexit);
        next = findViewById(R.id.btnnext);
    }

    private void Showtt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin bản đồ!");
        View view = getLayoutInflater().inflate(R.layout.choosedialog_layout, null);
        builder.setView(view);

        TextView showmap = view.findViewById(R.id.txtshowmap);
        TextView showtt = view.findViewById(R.id.txtshowtt);
        Button thoat = view.findViewById(R.id.btntthoat);

        if (rdo1.isChecked()) {
            showmap.setText(rdo1.getText());
        } else if (rdo2.isChecked()) {
            showmap.setText(rdo2.getText());
        } else if (rdo3.isChecked()) {
            showmap.setText(rdo3.getText());
        } else {
            showmap.setText(rdo4.getText());
        }

        StringBuilder services = new StringBuilder();
        if (cb1.isChecked()) {
            services.append(cb1.getText());
        }
        if (cb2.isChecked()) {
            if (services.length() > 0) {
                services.append(", ");
            }
            services.append(cb2.getText());
        }
        if (services.length() > 0) {
            showtt.setText(services.toString());
        } else {
            showtt.setText("Không có thuộc tính được chọn");
        }

        AlertDialog dialog = builder.create();
        dialog.show();

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
