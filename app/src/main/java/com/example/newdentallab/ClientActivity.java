package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientActivity extends AppCompatActivity {
    Button b1,b2,b3,b5;
    EditText n,a,p,e;
    data details;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        details=new data(this);
        sqLiteDatabase=details.getWritableDatabase();
        callFindViewByIdes();
        callOnClickListener();
    }

    private void callOnClickListener() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=n.getText().toString();
                String address=a.getText().toString();
                String phone=p.getText().toString();
                String email=e.getText().toString();
                Boolean i=details.insert(name,address,phone,email);
                if(i==true){
                    Toast.makeText(ClientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ClientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String namedoc=n.getText().toString();
                String phonedoc=p.getText().toString();
                Boolean i=details.delete_client(namedoc,phonedoc);
                if(i==true){
                    Toast.makeText(ClientActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ClientActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientActivity.this,ViewActivity.class));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientActivity.this,MainActivity.class));
            }
        });

    }


    private void callFindViewByIdes() {
        n=(EditText)findViewById(R.id.namevalue);
        a=(EditText)findViewById(R.id.PostalAddress);
        p=(EditText)findViewById(R.id.phonevalue);
        e=(EditText)findViewById(R.id.Emailvalue);
        b1=(Button)findViewById(R.id.addClient);
        b2=(Button)findViewById(R.id.deleteClient);
        b3=(Button)findViewById(R.id.viewClient);
        b5=(Button)findViewById(R.id.idbackdoc);

    }

}