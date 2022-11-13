package com.esprit.finddoc.Activities.Doctor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.esprit.finddoc.Activities.LoginActivity;
import com.esprit.finddoc.Activities.MainActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;

import java.util.Calendar;

public class DoctorProfileFragment extends Fragment {

    private TextView docName,docEmail,docAdress;
    private Button logoutbtn;



    public DoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_profile, container, false);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", this.getContext().MODE_PRIVATE);


        int id=sh.getInt("currentUserId", 0);
        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        User currentdoc = userDao.getUserById(id);

        docName = view.findViewById(R.id.TVdoctorfullname);
        docEmail = view.findViewById(R.id.TVdoctoremail);
        docAdress = view.findViewById(R.id.TVdoctoradress);
        logoutbtn = view.findViewById(R.id.logoutbtn);

        docName.setText(currentdoc.getFullName());
        docAdress.setText(currentdoc.getAdress());
        docEmail.setText(currentdoc.getEmail());

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh.edit().clear().apply();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        return view;
    }
}