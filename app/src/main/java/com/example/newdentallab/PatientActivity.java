package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientActivity extends AppCompatActivity {
    datatwo d;
    EditText a,b,c,e,f,am;
    Button add,delete,viewp,invoice,back;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        d=new datatwo(this);
        sqLiteDatabase=d.getWritableDatabase();
        callFindViewByIdes();
        callOnClickListener();
    }

    private void callOnClickListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=a.getText().toString();
                String address=b.getText().toString();
                String phone=c.getText().toString();
                String email=e.getText().toString();
                Boolean i=d.insert(name,address,phone,email);
                if(i==true){
                    Toast.makeText(PatientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PatientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this,ViewPatientActivity.class));

            }
        });


        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String namepat=a.getText().toString();
                String phpat=c.getText().toString();
                Boolean i=d.delete_patient(namepat,phpat);
                if(i==true){
                    Toast.makeText(PatientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PatientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this,MainActivity.class));
            }
        });
    }


    private void callFindViewByIdes() {
        a=(EditText)findViewById(R.id.idname);
        b=(EditText)findViewById(R.id.idaddress);
        c=(EditText)findViewById(R.id.idphone);
        e=(EditText)findViewById(R.id.idemail);
        add=(Button)findViewById(R.id.idadd);
        delete=(Button)findViewById(R.id.iddelete);
        viewp=(Button)findViewById(R.id.idview);
        back=(Button)findViewById(R.id.idback);
    }
}