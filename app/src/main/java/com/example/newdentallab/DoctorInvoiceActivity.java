package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorInvoiceActivity extends AppCompatActivity {
    final static int REQUEST_CODE=1232;
    Bitmap bmp,scaledBitmap;
    Button pdfinvoice,back;
    data dbs;
    DatabaseHelper databasehelper;
    SQLiteDatabase sqLiteDatabase,sq;
    Date date=new Date();
    String datePattern="dd-MM-yyyy";
    SimpleDateFormat datePatternFormat=new SimpleDateFormat(datePattern);

    String timePattern="hh:mm a";
    SimpleDateFormat timePatternFormat=new SimpleDateFormat(timePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_invoice);
        databasehelper=new DatabaseHelper(this);
        sqLiteDatabase=databasehelper.getWritableDatabase();
        dbs=new data(this);
        sq=dbs.getWritableDatabase();
        Intent intent = getIntent();
        String ids = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String docid = intent.getStringExtra("DOCID");
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.pdflogo);
        scaledBitmap=Bitmap.createScaledBitmap(bmp,200,180,false);
        askPermissions();
        findview();
        pdfinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfDocument mypdfDocument= new PdfDocument();
                Paint myPaint=new Paint();
                PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(1000,900,1).create();
                PdfDocument.Page myPage=mypdfDocument.startPage(myPageInfo);
                Canvas canvas=myPage.getCanvas();
                Long d=date.getTime();
                String[] column1={"id","Name","Phone","item_name1","quantity1","price1","item_name2","quantity2","price2","item_name3","quantity3","price3","item_name4","quantity4","price4","item_name5","quantity5","price5","amount"};
                String sel="id=?";
                String[] selar={ids};
                Cursor cursor1=sqLiteDatabase.query("items1",column1,sel,selar,null,null,null);
                cursor1.move(cursor1.getCount());
                String[] column2={"accountNo","Address","Email","contactNo"};
                String selection="accountNo=? AND Name=?";
                String[] selectionArgs={docid,name};
                Cursor cursor2=sq.query("client",column2,selection,selectionArgs,null,null,null);
                cursor2.move(cursor2.getCount());

                canvas.drawBitmap(scaledBitmap,0,0,myPaint);

                myPaint.setTextSize(60);
                Typeface customTypeface = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    customTypeface = getResources().getFont(R.font.pacifico);
                }
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Pankhuri Dental Lab", 170, 110, myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));

                myPaint.setColor(Color.rgb(150,150,150));
                canvas.drawRect(30,150,canvas.getWidth()-30,160,myPaint);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Doctor Name:",30,200,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextSize(20);
                canvas.drawText(cursor1.getString(1),280,200,myPaint);
                myPaint.setTextSize(20);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Date:",620,200,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(datePatternFormat.format(d),canvas.getWidth()-40,200,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Address:",30,245,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextSize(20);
                canvas.drawText(cursor2.getString(1),280,245,myPaint);
                myPaint.setTextSize(20);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Time:",620,245,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(timePatternFormat.format(d),canvas.getWidth()-40,245,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Phone:",30,290,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextSize(20);
                canvas.drawText(cursor2.getString(3),280,290,myPaint);
                myPaint.setTextSize(20);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Email:",30,335,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(20);
                canvas.drawText(cursor2.getString(2),280,335,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setColor(Color.rgb(150,150,150));
                canvas.drawRect(30,400,canvas.getWidth()-30,450,myPaint);
                myPaint.setTextSize(30);
                myPaint.setColor(Color.WHITE);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Orders",50,435,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Qty",545,435,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(30);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Amount",canvas.getWidth()-40,435,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(cursor1.getString(3),50,480,myPaint);
                canvas.drawText(String.valueOf(cursor1.getInt(4)),550,480,myPaint);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(String.valueOf(cursor1.getInt(5)),canvas.getWidth()-40,480,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(cursor1.getString(6),50,525,myPaint);
                canvas.drawText(String.valueOf(cursor1.getInt(7)),550,525,myPaint);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(String.valueOf(cursor1.getInt(8)),canvas.getWidth()-40,525,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(cursor1.getString(9),50,570,myPaint);
                canvas.drawText(String.valueOf(cursor1.getInt(10)),550,570,myPaint);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(String.valueOf(cursor1.getInt(11)),canvas.getWidth()-40,570,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(cursor1.getString(12),50,615,myPaint);
                canvas.drawText(String.valueOf(cursor1.getInt(13)),550,615,myPaint);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(String.valueOf(cursor1.getInt(14)),canvas.getWidth()-40,615,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setTextSize(20);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(cursor1.getString(15),50,660,myPaint);
                canvas.drawText(String.valueOf(cursor1.getInt(16)),550,660,myPaint);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                myPaint.setTextSize(20);
                canvas.drawText(String.valueOf(cursor1.getInt(17)),canvas.getWidth()-40,660,myPaint);
                myPaint.setTextAlign(Paint.Align.LEFT);

                myPaint.setColor(Color.rgb(150,150,150));
                canvas.drawRect(30,700,canvas.getWidth()-30,710,myPaint);

                myPaint.setTextSize(30);
                myPaint.setColor(Color.BLACK);
                myPaint.setTypeface(customTypeface);
                canvas.drawText("Total",550,760,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));

                myPaint.setTextSize(30);
                myPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(String.valueOf(cursor1.getInt(18)),970,760,myPaint);
                myPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                mypdfDocument.finishPage(myPage);
                File downloadsDir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                String fileName="Doctor "+cursor2.getInt(0)+" Invoice "+cursor1.getInt(0)+".pdf";
                File file=new File(downloadsDir,fileName);
                try {
                    FileOutputStream fos=new FileOutputStream(file);
                    mypdfDocument.writeTo(fos);
                    mypdfDocument.close();
                    fos.close();
                    Toast.makeText(DoctorInvoiceActivity.this,"PDF Saved Successfully in Downloads Folder",Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mypdfDocument.close();
            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorInvoiceActivity.this,DocViewInvoiceActivity.class));
            }
        });


    }
    private void findview() {
        pdfinvoice=(Button)findViewById(R.id.PdfInvoiceDoctor);
        back=(Button)findViewById(R.id.backinvd);
    }

    private void askPermissions(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
    }
}