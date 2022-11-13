package com.esprit.finddoc.Activities.Patient;

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
import com.esprit.finddoc.adapters.PatientAppointmentListAdapter;
import com.esprit.finddoc.dao.AppointmentDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;

import java.util.List;

public class AppoinmentsListPatientFragment extends Fragment {

    RecyclerView recview;

    public AppoinmentsListPatientFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_appoinments_list_patient, container, false);

        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        AppointmentDao appointmentDao = db.appointmentDao();

        recview=view.findViewById(R.id.recviewPatientListAppointment);
        recview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", this.getContext().MODE_PRIVATE);


        int id=sh.getInt("currentUserId", 0);

        List<Appointment> appointments=appointmentDao.getAppointmentByPatient(id);

        PatientAppointmentListAdapter adapter=new PatientAppointmentListAdapter(appointments);
        recview.setAdapter(adapter);

        return view;


    }


}