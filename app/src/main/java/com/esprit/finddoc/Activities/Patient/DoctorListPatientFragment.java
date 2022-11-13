package com.esprit.finddoc.Activities.Patient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.esprit.finddoc.R;
import com.esprit.finddoc.adapters.DoctorListPatientAdapter;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class DoctorListPatientFragment extends Fragment {

    RecyclerView recview;
    EditText searchti;



    public DoctorListPatientFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_doctor_list_patient, container, false);

        AppDatabase db = Room.databaseBuilder(this.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        searchti = view.findViewById(R.id.searchNameTV);


        recview=view.findViewById(R.id.recviewDoctorListPatient);
        recview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<User> users=userDao.getAllDoctors();




        DoctorListPatientAdapter adapter=new DoctorListPatientAdapter(users);
        recview.setAdapter(adapter);

        searchti.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
                List<User> filterusers = new ArrayList<User>();

                for (User u : users) {
                    if (u.getFullName().contains(mEdit.toString())) {
                        filterusers.add(u);
                    }
                }

                DoctorListPatientAdapter adapter1=new DoctorListPatientAdapter(filterusers);
                recview.setAdapter(adapter1);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        return view;


    }


}