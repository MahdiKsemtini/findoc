package com.esprit.finddoc.Activities.Patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.esprit.finddoc.Activities.MainActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;


public class PatientProfileFragment extends Fragment {

    private TextView patName,patEmail,patAdress;
    private Button logoutbtn;


    public PatientProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_patient_profile, container, false);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", this.getContext().MODE_PRIVATE);


        int id=sh.getInt("currentUserId", 0);
        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        User currentpat = userDao.getUserById(id);

        patName = view.findViewById(R.id.TVpatientfullname);
        patEmail = view.findViewById(R.id.TVpatientemail);
        patAdress = view.findViewById(R.id.TVpatientadress);
        logoutbtn = view.findViewById(R.id.logoutbutton);

        patName.setText(currentpat.getFullName());
        patAdress.setText(currentpat.getAdress());
        patEmail.setText(currentpat.getEmail());

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