package com.example.cuahangbanhoa1.SignUp_Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbanhoa1.Product.MainProduct;
import com.example.cuahangbanhoa1.R;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView tvSignup;
    EditText edtUsername,edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper=new DatabaseHelper(this);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        tvSignup=findViewById(R.id.tvSignUp);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();
                if(username.equals("")|| password.equals("")){
                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(databaseHelper.checkUsernamePassword(username,password)==true){
                        Intent intent=new Intent(Login.this, MainProduct.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}