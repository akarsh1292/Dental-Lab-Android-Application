package com.example.newdentallab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbbal extends SQLiteOpenHelper {
    public dbbal(@Nullable Context context) {
        super(context,"Balance",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tabl="create table paid(Name TEXT,Phone INTEGER,Type TEXT,Orders TEXT,Amount INTEGER,Status TEXT);";
        sqLiteDatabase.execSQL(tabl);
        String table="create table unpaid(Name TEXT,Phone INTEGER,Type TEXT,Orders TEXT,Amount INTEGER,Status TEXT);";
        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public Boolean insert_paid(String Name,String Phone,String Type,String Orders,String Amount,String Status){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Phone",Phone);
        contentValues.put("Type",Type);
        contentValues.put("Orders",Orders);
        contentValues.put("Amount",Amount);
        contentValues.put("Status",Status);
        long r=sqLiteDatabase.insert("paid",null,contentValues);
        if(r==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean insert_unpaid(String Name,String Phone,String Type,String Orders,String Amount,String Status){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Phone",Phone);
        contentValues.put("Type",Type);
        contentValues.put("Orders",Orders);
        contentValues.put("Amount",Amount);
        contentValues.put("Status",Status);
        long r=sqLiteDatabase.insert("unpaid",null,contentValues);
        if(r==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete_paid(String Name,String Phone,String Status){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from paid where Name=? and Phone=? and Status=?",new String[]{Name,Phone,Status});
        if(cursor.getCount()>0){
            long r=db.delete("paid","Name=? and Phone=? and Status=?",new String[]{Name,Phone,Status});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public boolean delete_unpaid(String Name,String Phone,String Status){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from unpaid where Name=? and Phone=? and Status=?",new String[]{Name,Phone,Status});
        if(cursor.getCount()>0){
            long r=db.delete("unpaid","Name=? and Phone=? and Status=?",new String[]{Name,Phone,Status});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor view_paid(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from paid",null);
        return cursor;
    }
    public Cursor view_unpaid(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from unpaid",null);
        return cursor;
    }


}

