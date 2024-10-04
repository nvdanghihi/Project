package com.example.json;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText user,pass;
    private Button login,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        khaibao();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginkhohang();
            }

            private void loginkhohang() {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String jsonData = "{ \"user\": \"admin\", \"pass\": \"1234\" }";
                try {
                    JSONObject jsonObject = new JSONObject(jsonData);
                    String user = jsonObject.getString("user");
                    String pass = jsonObject.getString("pass");

                    if (username.equals(user) && password.equals(pass)) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent menu= new Intent(LoginActivity.this,MenuActivity.class);
                        startActivity(menu);
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai tên người dùng hoặc mật khẩu.", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


    }

    private void khaibao() {
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        exit=findViewById(R.id.btnexxit);
    }
}
