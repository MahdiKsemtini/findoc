package com.esprit.finddoc.Activities.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.esprit.finddoc.Activities.MainActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.adapters.AdminDoctorListAdapter;
import com.esprit.finddoc.adapters.DoctorAppointmentListAdapter;
import com.esprit.finddoc.dao.AppointmentDao;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorListAdminFragment extends Fragment {

    RecyclerView recview;
    ImageView btnlogout;


    public DoctorListAdminFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_doctor_list_admin, container, false);

        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        recview=view.findViewById(R.id.recviewDoctorListAdmin);
        recview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        btnlogout=view.findViewById(R.id.buttonlogout2);

        List<User> users=userDao.getAllDoctors();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getEtat().compareTo(u2.getEtat());
            }
        });

        AdminDoctorListAdapter adapter=new AdminDoctorListAdapter(users);
        recview.setAdapter(adapter);

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", this.getContext().MODE_PRIVATE);

        btnlogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sh.edit().clear().apply();
                        Intent intent = new Intent(btnlogout.getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }
        );

        return view;


    }
}