package com.example.newdentallab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class data extends SQLiteOpenHelper {
    public data(@Nullable Context context) {
        super(context, "DatabaseForClient", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tabl="create table client(accountNo INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Address Text,contactNo INTEGER,Email INTEGER);";
        sqLiteDatabase.execSQL(tabl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public Boolean insert(String Name,String Address,String contactNo,String Email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Address",Address);
        contentValues.put("contactNo",contactNo);
        contentValues.put("Email",Email);
        long r=sqLiteDatabase.insert("client",null,contentValues);
        if(r==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor view_client(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from client",null);
        return cursor;
    }

    public boolean delete_client(String Name,String contactNo){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from client where Name=? and contactNo=?",new String[]{Name,contactNo});
        if(cursor.getCount()>0){
            long r=db.delete("client","Name=? and contactNo=?",new String[]{Name,contactNo});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

}
