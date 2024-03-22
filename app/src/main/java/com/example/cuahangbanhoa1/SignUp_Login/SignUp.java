package com.example.cuahangbanhoa1.SignUp_Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbanhoa1.R;

public class SignUp extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtName,edtUsername,edtPassword,edtDate;
    TextView tvLogin;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseHelper=new DatabaseHelper(this);
        edtName= findViewById(R.id.edtName);
        edtUsername= findViewById(R.id.edtUsername);
        edtPassword= findViewById(R.id.edtPassword);
        edtDate= findViewById(R.id.edtDate);
        tvLogin=findViewById(R.id.tvLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edtName.getText().toString();
                String username=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();
                String date=edtDate.getText().toString();
                if(name.equals("")||username.equals("")||password.equals("")||date.equals("")){
                    Toast.makeText(SignUp.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUsername= databaseHelper.checkUsername(username);
                    if(checkUsername==false){
                        Boolean insert =databaseHelper.insertData(name,username,password,date);
                        if(insert==true) {
                            Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                        }
                        else Toast.makeText(SignUp.this, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}