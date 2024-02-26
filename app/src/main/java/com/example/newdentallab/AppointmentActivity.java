package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AppointmentActivity extends AppCompatActivity {
    datatwo d;
    EditText name,dob,procdate,phone,service;
    Button add,remove,view,back;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        d=new datatwo(this);
        sqLiteDatabase=d.getWritableDatabase();
        callFindViewByIdes();
        callOnClickListener();
    }

    private void callOnClickListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String doap=dob.getText().toString();
                String prod=procdate.getText().toString();
                String ph=phone.getText().toString();
                String se=service.getText().toString();
                Boolean ia=d.add_appoint(n,doap,prod,ph,se);
                if(ia==true){
                    Toast.makeText(AppointmentActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AppointmentActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String namecl=name.getText().toString();
                String phoneap=phone.getText().toString();
                Boolean i=d.delete_appoint(namecl,phoneap);
                if(i==true){
                    Toast.makeText(AppointmentActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AppointmentActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentActivity.this,ViewAppointmentActivity.class));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentActivity.this,MainActivity.class));

            }
        });
    }

    private void callFindViewByIdes() {
        name=(EditText)findViewById(R.id.apname);
        dob=(EditText)findViewById(R.id.dobAp);
        procdate=(EditText)findViewById(R.id.procedureDate);
        phone=(EditText)findViewById(R.id.phoneAp);
        service=(EditText)findViewById(R.id.serviceAp);
        add=(Button)findViewById(R.id.addAp);
        remove=(Button)findViewById(R.id.removeAp);
        view=(Button)findViewById(R.id.viewAp);
        back=(Button)findViewById(R.id.backAp);

    }
}