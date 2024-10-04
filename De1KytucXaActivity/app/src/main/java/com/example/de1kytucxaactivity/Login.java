package com.example.de1kytucxaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText user,pass;
    Button exit,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        khaibao();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }

            private void dangnhap() {
                String username=user.getText().toString();
                String password=pass.getText().toString();
                if(username.equals("Danghihi") && password.equals("123"))
                {
                    Intent intent= new Intent(Login.this,kitucActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Nhập sai tài khoản hoặc mật khẩu!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void khaibao() {
        user=findViewById(R.id.txtuser);
        pass=findViewById(R.id.txtpass);
        exit=findViewById(R.id.btnexit);
        login=findViewById(R.id.btnlogin);

    }
}
