package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BalanceActivity extends AppCompatActivity {
    dbbal dbnews;
    Button b1,b2,b3,b4,b5;
    EditText n,p,t,od,amt,stat;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        dbnews=new dbbal(this);
        sqLiteDatabase=dbnews.getWritableDatabase();
        callFindViewByIdes();
        callOnClickListener();    }

    private void callOnClickListener() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=n.getText().toString();
                String phone=p.getText().toString();
                String type=t.getText().toString();
                String order=od.getText().toString();
                String amount=amt.getText().toString();
                String status=stat.getText().toString();
                if(status.equalsIgnoreCase("PAID")){
                    Boolean i=dbnews.insert_paid(name,phone,type,order,amount,status);
                    if(i==true){
                        Toast.makeText(BalanceActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BalanceActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    }
                }
                if(status.equalsIgnoreCase("UNPAID")){
                    Boolean iu=dbnews.insert_unpaid(name,phone,type,order,amount,status);
                    if(iu==true){
                        Toast.makeText(BalanceActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BalanceActivity.this,"NOT SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nameb = n.getText().toString();
                String phoneb = p.getText().toString();
                String stats=stat.getText().toString();
                if (stats.equalsIgnoreCase("PAID")) {
                    Boolean ip = dbnews.delete_paid(nameb, phoneb, stats);
                    if (ip) {
                        Toast.makeText(BalanceActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BalanceActivity.this, "NOT SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    }
                } else if (stats.equalsIgnoreCase("UNPAID")) {
                    Boolean iun = dbnews.delete_unpaid(nameb, phoneb, stats);
                    if (iun) {
                        Toast.makeText(BalanceActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BalanceActivity.this, "NOT SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BalanceActivity.this,MainActivity.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BalanceActivity.this,PaidBalanceActivity.class));
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BalanceActivity.this,UnpaidBalanceActivity.class));
            }
        });


    }

    private void callFindViewByIdes() {
        n=(EditText)findViewById(R.id.namevalueb);
        p=(EditText)findViewById(R.id.phonevalueb);
        t=(EditText)findViewById(R.id.TypeValue);
        od=(EditText)findViewById(R.id.ordervalueb);
        amt=(EditText)findViewById(R.id.amtvalueb);
        stat=(EditText)findViewById(R.id.statusvalue);
        b1=(Button)findViewById(R.id.add);
        b2=(Button)findViewById(R.id.delete);
        b3=(Button)findViewById(R.id.viewPaid);
        b5=(Button)findViewById(R.id.viewUnPaid);
        b4=(Button)findViewById(R.id.backed);

    }
}