package com.example.patientadminstration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ViewPatientReading extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_reading);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_members);
        new FirebaseDatabaseHelper().readMember(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Member> members, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, ViewPatientReading.this, members, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
