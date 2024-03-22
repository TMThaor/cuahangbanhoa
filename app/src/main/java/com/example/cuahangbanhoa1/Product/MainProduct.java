package com.example.cuahangbanhoa1.Product;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbanhoa1.Product.Model.Product;
import com.example.cuahangbanhoa1.Product.adapter.ProductAdapter;
import com.example.cuahangbanhoa1.R;
import com.example.cuahangbanhoa1.SignUp_Login.AccountDatabaseHelper;
import com.example.cuahangbanhoa1.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainProduct extends AppCompatActivity {

    AccountDatabaseHelper database;
    ArrayList<Product> listProduct;
    RecyclerView rvProduct;
    ProductAdapter rvAdapter;
//    FloatingActionButton add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product);

       rvProduct=findViewById(R.id.rvProduct);
//        add=findViewById(R.id.floatingActionButton);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                autoInsertDatabase();
//            }
//        });
       listProduct=new ArrayList<>();
       database=new AccountDatabaseHelper(this);
        System.out.println(database);


       initData();



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(mLayoutManager);



        rvAdapter  = new ProductAdapter(this, listProduct);
        rvProduct.setAdapter(rvAdapter);

    }

    void initData(){
        Cursor cursor=database.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                Product p= new Product(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3));
                int resID = getResId(p.getImageId(), R.drawable.class);
                Uri imgUri = getUri(resID);
                p.setImage(imgUri);
                listProduct.add(p);
            }
        }
    }
    public Uri getUri (int resId){
        return Uri.parse("android.resource://"  + this.getPackageName().toString() + "/" + resId);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    void autoInsertDatabase(){
        for (int i = 1; i <= 10; i++) {
            String name="Hoa "+i;
            String description="Mô tả hoa "+i;
            double price=999.9;
            String imageId="h"+i;
            Boolean insert =database.insertProduct(name,description,price,imageId);
            if(insert==true)
                Toast.makeText(this, "đã thêm thành công", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

        }
    }
}