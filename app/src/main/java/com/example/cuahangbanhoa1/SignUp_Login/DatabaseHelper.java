package com.example.cuahangbanhoa1.SignUp_Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName="FlowerStore.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "FlowerStore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table products(name TEXT primary key, description TEXT, image_id TEXT, price REAL)");
        db.execSQL("create Table users(username TEXT primary key, password TEXT, name TEXT,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists products");
        db.execSQL("Drop table if exists users");
    }

    public Boolean insertData(String name,String username,String password, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("date",date);
        long result = sqLiteDatabase.insert("users",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    //Check username khi dang ky
    public Boolean checkUsername(String username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    //check username va password khi dang nhap
    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from users where username=? and password =?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean insertProduct(String name,String description,double price, String imageId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("price",price);
        contentValues.put("image_id",imageId);
        long result = sqLiteDatabase.insert("products",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Cursor readAllData(){
        String query="select *from products";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=null;
        if(sqLiteDatabase!=null){
            cursor=sqLiteDatabase.rawQuery(query,null);
        }
        return cursor;
    }

}
