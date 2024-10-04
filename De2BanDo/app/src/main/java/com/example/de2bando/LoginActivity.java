package com.example.de2bando;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText user,pass;
    private Button login;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        khaibao();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=user.getText().toString().trim();
                String password=pass.getText().toString().trim();
                if(username.equals("Danghihi") && password.equals("123"))
                {
                    Intent intent = new Intent(LoginActivity.this,BandoActivity.class);
                    startActivity(intent);
                }
                else if (username.equals("") && password.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void khaibao() {
        user=findViewById(R.id.txtuser);
        pass=findViewById(R.id.txtpass);
        login=findViewById(R.id.btnLogin);
    }
}
