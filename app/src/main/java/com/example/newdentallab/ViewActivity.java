package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    data g;
    Button backbutton;
    ListView client_list;
    ArrayList<String> ListItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        g=new data(this);

        ListItem = new ArrayList<>();

        client_list=(ListView)findViewById(R.id.clients_list);

        view_client();

        client_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item data
                String itemData = (String) parent.getItemAtPosition(position);

                // Parse the item data to extract Name and Phone
                String name = extractNameFromItemData(itemData);
                String phone = extractPhoneFromItemData(itemData);
                String acc=extractIDFromItemData(itemData);

                // Perform action with Name and Phone values
                // For example, display in a Toast
                Intent intent = new Intent(ViewActivity.this, InvoiceDoctorActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("PHONE", phone);
                intent.putExtra("ID",acc);
                startActivity(intent);
            }
        });



        backbutton=(Button)findViewById(R.id.BackButton);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,ClientActivity.class));
            }
        });

    }

    private void view_client() {
        Cursor cursor = g.view_client();
        if(cursor.getCount()==0){
            Toast.makeText(ViewActivity.this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                ListItem.add("Account ID::"+cursor.getInt(0)+"\n"+"Name::"+cursor.getString(1)+"\n"+"Address::"+cursor.getString(2)+"\n"+"Phone::"+cursor.getString(3)+"\n"+"Email::"+cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListItem);
            client_list.setAdapter(adapter);
        }
    }
    private String extractIDFromItemData(String itemData) {
        // Splitting the item data into lines
        String[] lines = itemData.split("\n");
        String acc = "";


        for (String line : lines) {
            if (line.startsWith("Account ID::")){
                acc = line.substring(12);
                break;
            }
        }

        return acc;
    }
    private String extractNameFromItemData(String itemData) {
        // Splitting the item data into lines
        String[] lines = itemData.split("\n");
        String name = "";

        // Finding the line containing Name and extracting the Name value
        for (String line : lines) {
            if (line.startsWith("Name::")) {
                // Removing the "Name::" prefix from the line to get the Name value
                name = line.substring(6);
                break;
            }
        }

        return name;
    }

    private String extractPhoneFromItemData(String itemData) {
        // Splitting the item data into lines
        String[] lines = itemData.split("\n");
        String phone = "";

        // Finding the line containing Phone and extracting the Phone value
        for (String line : lines) {
            if (line.startsWith("Phone::")) {
                // Removing the "Phone::" prefix from the line to get the Phone value
                phone = line.substring(7);
                break;
            }
        }

        return phone;
    }

}