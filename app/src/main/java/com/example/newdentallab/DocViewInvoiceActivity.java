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

public class DocViewInvoiceActivity extends AppCompatActivity {
    DatabaseHelper g;
    Button backbutton;
    ListView client_list;
    ArrayList<String> ListItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view_invoice);
        g=new DatabaseHelper(this);

        ListItem = new ArrayList<>();

        client_list=(ListView)findViewById(R.id.invoicedoc_list);

        view_docinvoice();

        client_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item data
                String itemData = (String) parent.getItemAtPosition(position);

                // Parse the item data to extract Name and Phone
                String idp = extractIDFromItemData(itemData);
                String name = extractNameFromItemData(itemData);
                String docid = extractDocIDFromItemData(itemData);


                // Perform action with Name and Phone values
                // For example, display in a Toast
                Intent intent = new Intent(DocViewInvoiceActivity.this,DoctorInvoiceActivity.class);
                intent.putExtra("ID", idp);
                intent.putExtra("NAME", name);
                intent.putExtra("DOCID", docid);

                startActivity(intent);
            }
        });



        backbutton=(Button)findViewById(R.id.BackButtonind);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocViewInvoiceActivity.this,InvoiceDoctorActivity.class));
            }
        });

    }

    private void view_docinvoice() {
        Cursor cursor = g.view_docinvoicedetail();
        if(cursor.getCount()==0){
            Toast.makeText(DocViewInvoiceActivity.this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                ListItem.add("Invoice ID::"+cursor.getInt(0)+"\n"+"Doctor A/C ID::"+cursor.getString(1)+"\n"+"Name::"+cursor.getString(2)+"\n"+"Phone::"+cursor.getString(3)+"\n"+"Order 1::"+cursor.getString(4)+"\n"+"Qty of order 1::"+cursor.getInt(5)+"\n"+"Price of Order 1::"+cursor.getInt(6)+"\n"+"Order 2::"+cursor.getString(7)+"\n"+"Qty of order 2::"+cursor.getInt(8)+"\n"+"Price of Order 2::"+cursor.getInt(9)+"\n"+"Order 3::"+cursor.getString(10)+"\n"+"Qty of order 3::"+cursor.getInt(11)+"\n"+"Price of Order 3::"+cursor.getInt(12)+"\n"+"Order 4::"+cursor.getString(13)+"\n"+"Qty of order 4::"+cursor.getInt(14)+"\n"+"Price of Order 4::"+cursor.getInt(15)+"\n"+"Order 5::"+cursor.getString(16)+"\n"+"Qty of order 5::"+cursor.getInt(17)+"\n"+"Price of Order 5::"+cursor.getInt(18)+"\n"+"Amount Total::"+cursor.getInt(19));
            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ListItem);
            client_list.setAdapter(adapter);
        }
    }
    private String extractIDFromItemData(String itemData) {
        // Splitting the item data into lines
        String[] lines = itemData.split("\n");
        String id = "";


        for (String line : lines) {
            if (line.startsWith("Invoice ID::")) {

                id = line.substring(12);
                break;
            }
        }

        return id;
    }
    private String extractDocIDFromItemData(String itemData) {
        String[] lines = itemData.split("\n");
        String id = "";


        for (String line : lines) {
            if (line.startsWith("Doctor A/C ID::")) {

                id = line.substring(15);
                break;
            }
        }

        return id;
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
}