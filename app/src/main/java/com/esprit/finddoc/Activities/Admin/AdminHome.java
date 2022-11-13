package com.esprit.finddoc.Activities.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.esprit.finddoc.Activities.Doctor.DoctorProfileFragment;
import com.esprit.finddoc.Activities.Doctor.ScheduleDoctorFragment;
import com.esprit.finddoc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationViewAdmin;

    DoctorListAdminFragment firstFragment = new DoctorListAdminFragment();
    PatientListAdminFragment secondFragment = new PatientListAdminFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigationViewAdmin  = findViewById(R.id.bottomNavigationViewadmin);

        getSupportFragmentManager().beginTransaction().replace(R.id.fladminFragment,firstFragment).commit();

        bottomNavigationViewAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.adminDoctors:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fladminFragment,firstFragment).commit();
                        return true;
                    case R.id.adminPatients:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fladminFragment,secondFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}