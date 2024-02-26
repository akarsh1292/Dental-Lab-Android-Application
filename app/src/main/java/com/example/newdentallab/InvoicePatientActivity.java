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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoicePatientActivity extends AppCompatActivity {
    final static int REQUEST_CODE=1234;
    Bitmap bmp,scaledBitmap;
    EditText a,b,c,d,e,f,g,aa,bb,cc,dd,ee,ap,bp,cp,dp,ep,ac;
    datatwo dbs;
    DatabaseHelper databasehelper;
    SQLiteDatabase sqLiteDatabase,sq;

    Button addinpat,viewinpat,deleteinpat,backinpat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_patient);
        databasehelper=new DatabaseHelper(this);
        sqLiteDatabase=databasehelper.getWritableDatabase();
        dbs=new datatwo(this);
        sq=dbs.getWritableDatabase();
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String phone = intent.getStringExtra("PHONE");
        String acc= intent.getStringExtra("ID");
        a=(EditText)findViewById(R.id.namevalueip);
        a.setText(name);
        b=(EditText)findViewById(R.id.phonevalueip);
        b.setText(phone);
        ac=(EditText)findViewById(R.id.accvalueip);
        ac.setText(acc);
        callviewsbyides();
        onclicklistener();
    }


    private void onclicklistener() {
        addinpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=ac.getText().toString();
                String name=a.getText().toString();
                String phone=b.getText().toString();
                String or1=c.getText().toString();
                String or2=d.getText().toString();
                String or3=e.getText().toString();
                String or4=f.getText().toString();
                String or5=g.getText().toString();
                int qud1 = parseInteger(aa.getText().toString());
                int qud2 = parseInteger(bb.getText().toString());
                int qud3 = parseInteger(cc.getText().toString());
                int qud4 = parseInteger(dd.getText().toString());
                int qud5 = parseInteger(ee.getText().toString());
                int pdoc1 = parseInteger(ap.getText().toString());
                int pdoc2 = parseInteger(bp.getText().toString());
                int pdoc3 = parseInteger(cp.getText().toString());
                int pdoc4 = parseInteger(dp.getText().toString());
                int pdoc5 = parseInteger(ep.getText().toString());
                Integer amt=qud1*pdoc1+qud2*pdoc2+qud3*pdoc3+qud4*pdoc4+qud5*pdoc5;
                Boolean i=databasehelper.addItemPat(account,name,phone,or1,qud1,pdoc1,or2,qud2,pdoc2,or3,qud3,pdoc3,or4,qud4,pdoc4,or5,qud5,pdoc5,amt);
                if(i==true){
                    Toast.makeText(InvoicePatientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InvoicePatientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }

            }
        });
        deleteinpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=a.getText().toString();
                String Phone=b.getText().toString();
                Boolean i=databasehelper.delete_patinvoice(Name,Phone);
                if(i==true){
                    Toast.makeText(InvoicePatientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InvoicePatientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewinpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvoicePatientActivity.this, PatViewInvoiceActivity.class));
            }
        });
        backinpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvoicePatientActivity.this,ViewPatientActivity.class));
            }
        });

    }



    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Handle invalid input gracefully
            return 0; // Default value when parsing fails
        }
    }

    private void callviewsbyides() {
        c=(EditText)findViewById(R.id.op1);
        d=(EditText)findViewById(R.id.op2);
        e=(EditText)findViewById(R.id.op3);
        f=(EditText)findViewById(R.id.op4);
        g=(EditText)findViewById(R.id.op5);
        aa=(EditText)findViewById(R.id.qp1);
        bb=(EditText)findViewById(R.id.qp2);
        cc=(EditText)findViewById(R.id.qp3);
        dd=(EditText)findViewById(R.id.qp4);
        ee=(EditText)findViewById(R.id.qp5);
        ap=(EditText) findViewById(R.id.pp1);
        bp=(EditText) findViewById(R.id.pp2);
        cp=(EditText) findViewById(R.id.pp3);
        dp=(EditText) findViewById(R.id.pp4);
        ep=(EditText) findViewById(R.id.pp5);
        addinpat=(Button)findViewById(R.id.addinvoicepat);
        viewinpat=(Button)findViewById(R.id.viewinvoicepat);
        deleteinpat=(Button)findViewById(R.id.deleteinvoicepat);
        backinpat=(Button)findViewById(R.id.backinvoicepat);
    }



}