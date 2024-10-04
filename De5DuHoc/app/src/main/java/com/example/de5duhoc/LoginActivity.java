package com.example.de5duhoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button exit,login;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        khaibao();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("Danghihi") && pass.equals("123"))
                {
                    Intent intent=new Intent(LoginActivity.this,TuVanDuhocACtivity.class);
                    startActivity(intent);
                }
                else {
                   Toast.makeText(getApplicationContext(),"Nhập sai tài khoản hoặc mật khẩu!!!",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    username.findFocus();
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
        username=findViewById(R.id.txtuser);
        password=findViewById(R.id.txtpass);
        exit=findViewById(R.id.btnexit);
        login=findViewById(R.id.btnlogin);
    }
}
