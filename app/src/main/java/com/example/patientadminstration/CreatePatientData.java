package com.example.patientadminstration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreatePatientData extends AppCompatActivity {
    EditText name,dob,address, mobile, heartrate, bp,blood,resprate, temp, IVname, IVrate, IVreading, medicalcond, BedNo;
    Button reset, cancel, save;
    int id = 0;
    FirebaseDatabase database;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_data);

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
                        notification(member.getBedNo().toString(), member.getName().toString(), member.getIVreading().toString());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String aname = name.getText().toString();
                String adob = dob.getText().toString();
                String aphone = mobile.getText().toString();
                String aaddress = address.getText().toString();
                String aheartrate = heartrate.getText().toString();
                String abp = bp.getText().toString();
                String ablood = blood.getText().toString();
                String aresprate = resprate.getText().toString();
                String atemp = temp.getText().toString();
                String aIVname = IVname.getText().toString();
                String aIVrate = IVrate.getText().toString();
                String aIVreading = IVreading.getText().toString();
                String amedicalcond = medicalcond.getText().toString();
                String aBedNo = BedNo.getText().toString();

                if(aBedNo.isEmpty() && aname.isEmpty() && adob.isEmpty() && aaddress.isEmpty() && aphone.isEmpty() && aheartrate.isEmpty() && abp.isEmpty() && ablood.isEmpty() && aresprate.isEmpty() && atemp.isEmpty() && aIVname.isEmpty() && aIVrate.isEmpty() && aIVreading.isEmpty() && amedicalcond.isEmpty()){
                    Toast.makeText(CreatePatientData.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else {
                    notification(aBedNo, aname,aIVreading);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //---------------------------------------------------------------------------------------------------------

        //Initialize the fields
        setup();
        //Cancel the screen
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreatePatientData.this, MainActivity.class));
            }
        });

        //Reset the fields
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("FirstName LastName");
                dob.setText("DOB dd/mm/yyyy");
                address.setText("Address");
                mobile.setText("Mobile Number");
                heartrate.setText("Heart Rate");
                bp.setText("BP");
                blood.setText("Blood Group");
                resprate.setText("Respiratory Rate");
                temp.setText("Temperature");
                IVname.setText("IVName");
                IVrate.setText("IV Infusion Rate");
                IVreading.setText("Current IV Reading");
                medicalcond.setText("Medical Condition");
                BedNo.setText("BedNo");
            }
        });

        //Save the record to firebase

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               member.setName(name.getText().toString());
                member.setDob(dob.getText().toString());
                member.setMobile(mobile.getText().toString());
                member.setAddress(address.getText().toString());
                member.setHeartrate(heartrate.getText().toString());
                member.setBp(bp.getText().toString());
                member.setBlood(blood.getText().toString());
                member.setResprate(resprate.getText().toString());
                member.setTemp(temp.getText().toString());
                member.setIVname(IVname.getText().toString());
                member.setIVrate(IVrate.getText().toString());
                member.setIVreading(IVreading.getText().toString());
                member.setMedicalcond(medicalcond.getText().toString());
                member.setBedNo(BedNo.getText().toString());

                reff.child(String.valueOf(id+1)).setValue(member);
                Toast.makeText(CreatePatientData.this, "Patient record:" + name.getText().toString() + " is created successfully", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void setup(){
        name = (EditText)findViewById(R.id.etName);
        dob = (EditText)findViewById(R.id.etDOB);
        address =  (EditText)findViewById(R.id.etAddress);
        mobile =  (EditText)findViewById(R.id.etMobile);
        heartrate =  (EditText)findViewById(R.id.etHeartRate);
        bp =  (EditText)findViewById(R.id.etBP);
        blood =  (EditText)findViewById(R.id.etBG);
        resprate =  (EditText)findViewById(R.id.etRespiratoryRate);
        temp =  (EditText)findViewById(R.id.etTemp);
        IVname =  (EditText)findViewById(R.id.etIVName);
        IVrate =  (EditText)findViewById(R.id.etInfusionRate);
        IVreading =  (EditText)findViewById(R.id.etIVReading);
        medicalcond =  (EditText)findViewById(R.id.etMedicalCondition);
        reset = (Button)findViewById(R.id.btnReset);
        cancel = (Button)findViewById(R.id.btnCancel);
        save = (Button)findViewById(R.id.btnSave);
        BedNo = (EditText)findViewById(R.id.etBedNo);
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
