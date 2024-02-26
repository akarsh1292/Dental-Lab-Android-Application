package com.example.newdentallab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class datatwo extends SQLiteOpenHelper {
    public datatwo(@Nullable Context context) {
        super(context, "DatabaseAll", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablpat="create table patient(accountNo INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Address Text,contactNo INTEGER,Email INTEGER);";
        String appo="create table appointment(NAME text,DOB text,PROCEDURE_DATE text,PHONE integer,SERVICE text);";
        db.execSQL(tablpat);
        db.execSQL(appo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String Name,String Address,String contactNo,String Email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Address",Address);
        contentValues.put("contactNo",contactNo);
        contentValues.put("Email",Email);
        long r=sqLiteDatabase.insert("patient",null,contentValues);
        if(r==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor view_patient(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from patient",null);
        return cursor;
    }
    public boolean delete_patient(String Name,String contactNo){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from patient where Name=? and contactNo=?",new String[]{Name,contactNo});
        if(cursor.getCount()>0){
            long r=db.delete("patient","Name=? and contactNo=?",new String[]{Name,contactNo});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean add_appoint(String NAME,String DOB,String PROCEDURE_DATE,String PHONE,String SERVICE){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("NAME",NAME);
        c.put("DOB",DOB);
        c.put("PROCEDURE_DATE",PROCEDURE_DATE);
        c.put("PHONE",PHONE);
        c.put("SERVICE",SERVICE);
        long rs=sqLiteDatabase.insert("appointment",null,c);
        if(rs==-1){
            return false;
        }else{
            return true;
        }

    }

    public boolean delete_appoint(String NAME,String PHONE){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from appointment where NAME=? and PHONE=?",new String[]{NAME,PHONE});
        if(cursor.getCount()>0){
            long r=db.delete("appointment","NAME=? and PHONE=?",new String[]{NAME,PHONE});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor view_appoint(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from appointment",null);
        return cursor;
    }
}
