package com.example.newdentallab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView Doctor= findViewById(R.id.cardDoctor);
        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ClientActivity.class));
            }
        });
        CardView Patient= findViewById(R.id.cardPatient);
        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PatientActivity.class));
            }
        });
        CardView Appointment= findViewById(R.id.cardAppointment);
        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AppointmentActivity.class));
            }
        });
        CardView Balance= findViewById(R.id.cardBalance);
        Balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BalanceActivity.class));
            }
        });


        ImageButton contactButton=(ImageButton) findViewById(R.id.imageButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
            }
        });

    }

}