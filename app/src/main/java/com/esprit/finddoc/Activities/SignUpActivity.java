package com.esprit.finddoc.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText user_name, user_type, user_email, user_adress;
    private EditText user_password;
    private Button signUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user_name = findViewById(R.id.editTextName);
        user_type = findViewById(R.id.editTextType);
        user_email = findViewById(R.id.editTextEmail);
        user_password = findViewById(R.id.editTextPassword);
        user_adress = findViewById(R.id.editTextAdresse);

        signUpBtn = findViewById(R.id.button_signup);

        // adding on click listener for our save button.
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String userName = user_name.getText().toString();
                String userType = user_type.getText().toString();
                String userEmail = user_email.getText().toString();
                String userPassword = user_password.getText().toString();
                String userAdress = user_adress.getText().toString();
                if (userName==null || userType==null || userEmail==null || userPassword==null) {
                    Toast.makeText(SignUpActivity.this, "Please enter the valid user details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    User usr = new User(userName,userType,userAdress,userEmail,userPassword,false);
                    Log.d("username",userName);
                    Log.d("type",userType);
                    Log.d("email",userEmail);
                    Log.d("password",userPassword);
                    Log.d("adress",userAdress);

                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "room_db").allowMainThreadQueries().build();
                    UserDao userDao = db.userDao();
                    Boolean check=userDao.is_exist(userEmail);
                    if(check==false) {
                        userDao.insertrecord(usr);
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                }

            }
        });


    }

}