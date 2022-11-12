package com.esprit.finddoc.Activities.Patient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.finddoc.R;
import com.esprit.finddoc.adapters.DoctorListPatientAdapter;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;

import java.util.List;


public class DoctorListPatientFragment extends Fragment {

    RecyclerView recview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorListPatientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doctor_list_patient, container, false);

        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        recview=view.findViewById(R.id.recviewDoctorListPatient);
        recview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<User> users=userDao.getAllDoctors();

        DoctorListPatientAdapter adapter=new DoctorListPatientAdapter(users);
        recview.setAdapter(adapter);
        return view;


    }


}