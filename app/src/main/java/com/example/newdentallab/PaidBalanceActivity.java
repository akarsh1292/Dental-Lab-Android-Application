package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PaidBalanceActivity extends AppCompatActivity {
    dbbal g;
    Button backbutton;
    ListView balance_list;
    ArrayList<String> ListItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_balance);
        g=new dbbal(this);

        ListItem = new ArrayList<>();

        balance_list=(ListView)findViewById(R.id.balances_list);

        view_balance();



        backbutton=(Button)findViewById(R.id.BackButtonbalance);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaidBalanceActivity.this,BalanceActivity.class));
            }
        });

    }

    private void view_balance() {
        Cursor cursor = g.view_paid();
        if(cursor.getCount()==0){
            Toast.makeText(PaidBalanceActivity.this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                ListItem.add("Name::"+cursor.getString(0)+"\n"+"Phone::"+cursor.getString(1)+"\n"+"Type::"+cursor.getString(2)+"\n"+"Order::"+cursor.getString(3)+"\n"+"Amount::"+cursor.getString(4)+"\n"+"Status::"+cursor.getString(5));
            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListItem);
            balance_list.setAdapter(adapter);
        }
    }

}


