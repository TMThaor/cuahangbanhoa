package com.example.cuahangbanhoa1.Product;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbanhoa1.Product.Model.Product;
import com.example.cuahangbanhoa1.Product.adapter.ProductAdapter;
import com.example.cuahangbanhoa1.R;
import com.example.cuahangbanhoa1.SignUp_Login.DatabaseHelper;
import com.example.cuahangbanhoa1.databinding.ActivityMainBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class MainProduct extends AppCompatActivity {

    DatabaseHelper database;
    ArrayList<Product> listProduct;
    RecyclerView rvProduct;
    ProductAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product);

       rvProduct=findViewById(R.id.rvProduct);
       listProduct=new ArrayList<>();
       database=new DatabaseHelper(this);

       initData();
       if(listProduct.size()==0){
           autoInsertDatabase();
           initData();
       }



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
        Random rand=new Random();
        for (int i = 1; i <= 10; i++) {
            String name="Product "+i;
            String description="Mô tả của sản phẩm "+i;
            double price=rand.nextDouble()*100;
            String imageId="h"+i;
            database.insertProduct(name,description,price,imageId);
        }
    }
}