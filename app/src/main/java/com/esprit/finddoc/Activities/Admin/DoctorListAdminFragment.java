package com.esprit.finddoc.Activities.Admin;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.finddoc.R;
import com.esprit.finddoc.adapters.AdminDoctorListAdapter;
import com.esprit.finddoc.adapters.DoctorAppointmentListAdapter;
import com.esprit.finddoc.dao.AppointmentDao;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

import java.util.List;

public class DoctorListAdminFragment extends Fragment {

    RecyclerView recview;


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

        List<User> users=userDao.getAllDoctors();

        AdminDoctorListAdapter adapter=new AdminDoctorListAdapter(users);
        recview.setAdapter(adapter);

        return view;


    }
}