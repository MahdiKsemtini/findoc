package com.esprit.finddoc.Activities.Patient;

import static com.esprit.finddoc.Activities.MainActivity.NOTIFICATION_CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.esprit.finddoc.Activities.LoginActivity;
import com.esprit.finddoc.Activities.MainActivity;
import com.esprit.finddoc.Activities.SignUpActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.AppointmentDao;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.MyNotificationPublisher;
import com.esprit.finddoc.models.User;

import java.util.Calendar;

public class DoctorDetailPatientActivity extends AppCompatActivity {
    private final static String default_notification_channel_id = "default" ;

    private TextView docName,docEmail,docAdress;
    private Button bookBtn,btnTime,btnDate;
    private EditText txtDate, txtTime;

    private int mYear, mMonth, mDay, mHour, mMinute;
    int id=0;

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
        bookBtn = (Button)findViewById(R.id.bookAppBtn);



        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);


        int patId=sh.getInt("currentUserId", 0);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //The key argument here must match that used in the other activity
            docName.setText(extras.getString("username"));
            docEmail.setText(extras.getString("useremail"));
            docAdress.setText(extras.getString("useradress"));
            id=extras.getInt("userid");
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


        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                if (txtDate==null || txtTime==null ) {
                    Toast.makeText(DoctorDetailPatientActivity.this, "Please choose time and date.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Appointment apn = new Appointment(txtDate.getText().toString(),txtTime.getText().toString(),id,patId);

                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "room_db").allowMainThreadQueries().build();
                    AppointmentDao appointmentDao = db.appointmentDao();
                    Boolean check=appointmentDao.is_exist(txtDate.getText().toString(),txtTime.getText().toString(),id);
                    if(check==false) {
                        appointmentDao.insertrecord(apn);
                        Toast.makeText(DoctorDetailPatientActivity.this, "Appointment booked successfully", Toast.LENGTH_SHORT).show();
                        scheduleNotification(getNotification( "Your Appointment with Doctor : "+docName.getText()+" is tomorow!" ) , 30000 ) ;
                        return;
                    }
                    else {
                        Toast.makeText(DoctorDetailPatientActivity.this, "This time is already booked.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
        });



    }

    private void scheduleNotification (Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Appointment reminder" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
}

