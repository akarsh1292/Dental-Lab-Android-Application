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

public class ViewAppointmentActivity extends AppCompatActivity {
    datatwo g;
    Button backbutton;
    ListView appoint_list;
    ArrayList<String> ListItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        g=new datatwo(this);

        ListItem = new ArrayList<>();

        appoint_list=(ListView)findViewById(R.id.listAppoint);

        view_appointment();



        backbutton=(Button)findViewById(R.id.BackbuttonAp);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAppointmentActivity.this,AppointmentActivity.class));
            }
        });

    }

    private void view_appointment() {
        Cursor cursor = g.view_appoint();
        if(cursor.getCount()==0){
            Toast.makeText(ViewAppointmentActivity.this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                ListItem.add("Name::"+cursor.getString(0)+"\n"+"DOB::"+cursor.getString(1)+"\n"+"Procedure Date::"+cursor.getString(2)+"\n"+"Phone::"+cursor.getString(3)+"\n"+"Service::"+cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListItem);
            appoint_list.setAdapter(adapter);
        }
    }




}