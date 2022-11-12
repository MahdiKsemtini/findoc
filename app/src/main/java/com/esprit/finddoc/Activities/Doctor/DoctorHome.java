package com.esprit.finddoc.Activities.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.esprit.finddoc.Activities.Patient.AppoinmentsListPatientFragment;
import com.esprit.finddoc.Activities.Patient.DoctorListPatientFragment;
import com.esprit.finddoc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DoctorHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationViewDoctor;

    ScheduleDoctorFragment firstFragment = new ScheduleDoctorFragment();
    DoctorProfileFragment secondFragment = new DoctorProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        bottomNavigationViewDoctor  = findViewById(R.id.bottomNavigationViewdoc);

        getSupportFragmentManager().beginTransaction().replace(R.id.fldoctorFragment,firstFragment).commit();

        bottomNavigationViewDoctor.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.doctorCalendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fldoctorFragment,firstFragment).commit();
                        return true;
                    case R.id.doctorProfile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fldoctorFragment,secondFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}