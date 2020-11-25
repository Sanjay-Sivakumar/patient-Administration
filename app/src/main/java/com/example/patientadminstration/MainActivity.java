package com.example.patientadminstration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private Button btncreate;
   private Button btnview;
    FirebaseDatabase database;
    DatabaseReference reff;
    Member member;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        //Create Patient Record screen
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreatePatientData.class));
            }
        });

        //View Patient Readings
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPatientReading.class));
            }
        });

        //Database listeners **************************************************************************
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Patient");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    id = (int)dataSnapshot.getChildrenCount();
                }

                List<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Member member = keynode.getValue(Member.class);
                    Integer IVreadingindexstart = member.getIVreading().toString().indexOf(":");
                    Integer IVreadinglength = member.getIVreading().toString().length();
                    Integer CurrentIVReading = Integer.parseInt((member.getIVreading().toString().substring(IVreadingindexstart+2, IVreadinglength - 2).trim()));

                    if(CurrentIVReading < 100){
                        notification(member.getBedNo().toString(),member.getName().toString(), member.getIVreading().toString());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setup(){
        btncreate = (Button)findViewById(R.id.btnCreate);
        btnview = (Button)findViewById(R.id.btnViewPatient);
    }

    public void notification(String nBedNo, String nname, String nIVreading){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        if (!nIVreading.isEmpty()){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                    .setContentText("Code Sphere")
                    .setSmallIcon(R.drawable.ic_notifications_)
                    .setAutoCancel(true)
                    .setContentText(nIVreading + " left for patient in Bed#" + nBedNo);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(999, builder.build());

        }else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                    .setContentText("Code Sphere")
                    .setSmallIcon(R.drawable.ic_notifications_)
                    .setAutoCancel(true)
                    .setContentText("Patient record:" + nname + " is created successfully");
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(999, builder.build());
        }

    }
}
