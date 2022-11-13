package com.esprit.finddoc.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.finddoc.Activities.Doctor.DoctorHome;
import com.esprit.finddoc.Activities.Patient.PatientHome;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Array;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private EditText user_email,user_password;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_email = findViewById(R.id.editTextTextEmailAddress);
        user_password = findViewById(R.id.editTextTextPassword);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        List<User> users;
        users = userDao.getallusers();
        for (User user:users
             ) {
            Log.d("email",user.getEmail());
            Log.d("name",user.getFullName());
            Log.d("password",user.getPassword());
            Log.d("type",user.getType());

        }


        Button loginsubmitbtn = (Button)findViewById(R.id.buttonLoginSubmit);

        loginsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = user_email.getText().toString();
                String userPassword = user_password.getText().toString();
                Boolean check=userDao.is_exist(userEmail);
                if(check==true) {
                    u =userDao.getUserByEmail(userEmail);
                    Log.d("userId",String.valueOf(u.getId()));
                    if ((Objects.equals(u.getEmail(), userEmail))&&(Objects.equals(u.getPassword(), userPassword))) {
                        if (Objects.equals(u.getType(), "Patient")) {

                            startActivity(new Intent(LoginActivity.this, PatientHome.class));

                        } else if (Objects.equals(u.getType(), "Doctor")) {
                            startActivity(new Intent(LoginActivity.this, DoctorHome.class));

                        }

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putInt("currentUserId", u.getId());
                        myEdit.apply();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Please enter the valid user credentials.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }
}