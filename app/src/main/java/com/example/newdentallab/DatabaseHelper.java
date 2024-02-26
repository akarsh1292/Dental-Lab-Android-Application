package com.example.newdentallab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "InvoiceDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_INVOICE1 = "items1";
    private static final String TABLE_INVOICE2 = "items2";
    private static final String COLUMN_ID = "id";
    private static final String DOCID="docid";
    private static final String NAME="Name";
    private static final String PHONE="Phone";
    private static final String ITEMNAME1 = "item_name1";
    private static final String QUANTITY1 = "quantity1";
    private static final String PRICE1 = "price1";
    private static final String ITEMNAME2 = "item_name2";
    private static final String QUANTITY2 = "quantity2";
    private static final String PRICE2 = "price2";
    private static final String ITEMNAME3 = "item_name3";
    private static final String QUANTITY3 = "quantity3";
    private static final String PRICE3 = "price3";
    private static final String ITEMNAME4 = "item_name4";
    private static final String QUANTITY4 = "quantity4";
    private static final String PRICE4 = "price4";
    private static final String ITEMNAME5 = "item_name5";
    private static final String QUANTITY5 = "quantity5";
    private static final String PRICE5 = "price5";
    private static final String AMOUNT = "amount";
    private static final String COLUMN_ID2 = "id";
    private static final String PATID="patid";
    private static final String NAME2="Name";
    private static final String PHONE2="Phone";
    private static final String ITEMNAME12 = "item_name1";
    private static final String QUANTITY12 = "quantity1";
    private static final String PRICE12 = "price1";
    private static final String ITEMNAME22 = "item_name2";
    private static final String QUANTITY22 = "quantity2";
    private static final String PRICE22 = "price2";
    private static final String ITEMNAME32 = "item_name3";
    private static final String QUANTITY32 = "quantity3";
    private static final String PRICE32 = "price3";
    private static final String ITEMNAME42 = "item_name4";
    private static final String QUANTITY42 = "quantity4";
    private static final String PRICE42= "price4";
    private static final String ITEMNAME52 = "item_name5";
    private static final String QUANTITY52 = "quantity5";
    private static final String PRICE52 = "price5";
    private static final String AMOUNT2 = "amount";

    private static final String CREATE_TABLE_ITEMS1 = "CREATE TABLE " + TABLE_INVOICE1 + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DOCID + " TEXT, "
            + NAME + " TEXT, "
            + PHONE + " INTEGER, "
            + ITEMNAME1 + " TEXT, "
            + QUANTITY1 + " INTEGER, "
            + PRICE1 + " INTEGER, "
            + ITEMNAME2 + " TEXT, "
            + QUANTITY2 + " INTEGER, "
            + PRICE2 + " INTEGER, "
            + ITEMNAME3 + " TEXT, "
            + QUANTITY3 + " INTEGER, "
            + PRICE3 + " INTEGER, "
            + ITEMNAME4 + " TEXT, "
            + QUANTITY4 + " INTEGER, "
            + PRICE4 + " INTEGER, "
            + ITEMNAME5 + " TEXT, "
            + QUANTITY5 + " INTEGER, "
            + PRICE5 + " INTEGER, "
            + AMOUNT + " INTEGER)";
    private static final String CREATE_TABLE_ITEMS2 = "CREATE TABLE " + TABLE_INVOICE2 + "("
            + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PATID + " TEXT, "
            + NAME2 + " TEXT, "
            + PHONE2 + " INTEGER, "
            + ITEMNAME12 + " TEXT, "
            + QUANTITY12 + " INTEGER, "
            + PRICE12 + " INTEGER, "
            + ITEMNAME22 + " TEXT, "
            + QUANTITY22 + " INTEGER, "
            + PRICE22 + " INTEGER, "
            + ITEMNAME32 + " TEXT, "
            + QUANTITY32 + " INTEGER, "
            + PRICE32 + " INTEGER, "
            + ITEMNAME42 + " TEXT, "
            + QUANTITY42 + " INTEGER, "
            + PRICE42 + " INTEGER, "
            + ITEMNAME52 + " TEXT, "
            + QUANTITY52 + " INTEGER, "
            + PRICE52 + " INTEGER, "
            + AMOUNT2 + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEMS1);
        db.execSQL(CREATE_TABLE_ITEMS2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICE2);
        onCreate(db);
    }

    public Boolean addItemDoc(String acc,String Name,String Phone,String item_name1,Integer quantity1,Integer price1,String item_name2,Integer quantity2,Integer price2,String item_name3,Integer quantity3,Integer price3,String item_name4,Integer quantity4,Integer price4,String item_name5,Integer quantity5,Integer price5,Integer amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOCID,acc);
        values.put(NAME,Name);
        values.put(PHONE,Phone);
        values.put(ITEMNAME1,item_name1);
        values.put(QUANTITY1,quantity1);
        values.put(PRICE1,price1);
        values.put(ITEMNAME2,item_name2);
        values.put(QUANTITY2,quantity2);
        values.put(PRICE2,price2);
        values.put(ITEMNAME3,item_name3);
        values.put(QUANTITY3,quantity3);
        values.put(PRICE3,price3);
        values.put(ITEMNAME4,item_name4);
        values.put(QUANTITY4,quantity4);
        values.put(PRICE4,price4);
        values.put(ITEMNAME5,item_name5);
        values.put(QUANTITY5,quantity5);
        values.put(PRICE5,price5);
        values.put(AMOUNT,amount);
        long id = db.insert(TABLE_INVOICE1, null, values);
        if(id==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor view_docinvoicedetail(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_INVOICE1,null);
        return cursor;
    }
    public boolean delete_docinvoice(String Name,String Phone){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from items1 where Name=? and Phone=?",new String[]{Name,Phone});
        if(cursor.getCount()>0){
            long r=db.delete(TABLE_INVOICE1,"Name=? and Phone=?",new String[]{Name,Phone});
            if(r==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean addItemPat(String acc,String Name,String Phone,String item_name1,Integer quantity1,Integer price1,String item_name2,Integer quantity2,Integer price2,String item_name3,Integer quantity3,Integer price3,String item_name4,Integer quantity4,Integer price4,String item_name5,Integer quantity5,Integer price5,Integer amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PATID,acc);
        values.put(NAME2,Name);
        values.put(PHONE2,Phone);
        values.put(ITEMNAME12,item_name1);
        values.put(QUANTITY12,quantity1);
        values.put(PRICE12,price1);
        values.put(ITEMNAME22,item_name2);
        values.put(QUANTITY22,quantity2);
        values.put(PRICE22,price2);
        values.put(ITEMNAME32,item_name3);
        values.put(QUANTITY32,quantity3);
        values.put(PRICE32,price3);
        values.put(ITEMNAME42,item_name4);
        values.put(QUANTITY42,quantity4);
        values.put(PRICE42,price4);
        values.put(ITEMNAME52,item_name5);
        values.put(QUANTITY52,quantity5);
        values.put(PRICE52,price5);
        values.put(AMOUNT2,amount);
        long id = db.insert(TABLE_INVOICE2, null, values);
        if(id==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean delete_patinvoice(String Name,String Phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from items2 where Name=? and Phone=?", new String[]{Name, Phone});
        if (cursor.getCount() > 0) {
            long r = db.delete(TABLE_INVOICE2, "Name=? and Phone=?", new String[]{Name, Phone});
            if (r == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor view_patinvoicedetail(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_INVOICE2,null);
        return cursor;
    }

}
