package com.esprit.finddoc.Activities.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.esprit.finddoc.Activities.LoginActivity;
import com.esprit.finddoc.Activities.MainActivity;
import com.esprit.finddoc.R;

import java.util.Calendar;

public class DoctorDetailPatientActivity extends AppCompatActivity {

    private TextView docName,docEmail,docAdress;
    private Button bookBtn,btnTime,btnDate;
    private EditText txtDate, txtTime;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail_patient);

        docName = findViewById(R.id.doctorfullnameTV);
        docEmail = findViewById(R.id.doctoremailTV);
        docAdress = findViewById(R.id.doctoradressTV);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        btnDate = (Button)findViewById(R.id.btn_date);
        btnTime = (Button)findViewById(R.id.btn_time);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            docName.setText(extras.getString("username"));
            docEmail.setText(extras.getString("useremail"));
            docAdress.setText(extras.getString("useradress"));
        }

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(btnDate.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(btnTime.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

    }
}