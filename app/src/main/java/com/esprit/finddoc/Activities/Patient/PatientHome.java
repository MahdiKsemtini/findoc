package com.esprit.finddoc.Activities.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.esprit.finddoc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PatientHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    DoctorListPatientFragment firstFragment = new DoctorListPatientFragment();
    AppoinmentsListPatientFragment secondFragment = new AppoinmentsListPatientFragment();
    PatientProfileFragment thirdFragment = new PatientProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,firstFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.patientDoctors:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,firstFragment).commit();
                        return true;
                    case R.id.patientCalendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,secondFragment).commit();
                        return true;
                    case R.id.patientProfile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,thirdFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}