package com.example.cuahangbanhoa1.SignUp_Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbanhoa1.Product.MainProduct;
import com.example.cuahangbanhoa1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    TextView tvSignup;
    EditText edtUsername,edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        databaseHelper=new DatabaseHelper(this);
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

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username=edtUsername.getText().toString();
//                String password=edtPassword.getText().toString();
//                if(username.equals("")|| password.equals("")){
//                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    if(databaseHelper.checkUsernamePassword(username,password)==true){
//                        Intent intent=new Intent(Login.this, MainProduct.class);
//                        startActivity(intent);
//                    }
//                    else
//                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }
    public void checkUser(){
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    edtUsername.setError(null);
                    String passwordDB = snapshot.child(username).child("password").getValue(String.class);

                    if(!Objects.equals(passwordDB,password)){
                        edtUsername.setError(null);
                        Intent intent = new Intent(Login.this, MainProduct.class);
                        startActivity(intent);
                    }else{
                        edtPassword.setError("Mat khau khong dung");
                        edtPassword.requestFocus();
                    }
                }else{
                    edtUsername.setError("Username khong ton tai");
                    edtUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}