package com.esprit.finddoc.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.esprit.finddoc.Activities.Patient.DoctorDetailPatientActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DoctorAppointmentListAdapter extends RecyclerView.Adapter<DoctorAppointmentListAdapter.myviewholder>{
    List<Appointment> appointments;



    public DoctorAppointmentListAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }



    @NonNull
    @NotNull
    @Override
    public DoctorAppointmentListAdapter.myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appointment_list_item,parent,false);


        return new DoctorAppointmentListAdapter.myviewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorAppointmentListAdapter.myviewholder holder, int position) {
        int idpat = appointments.get(position).getPatient_id();
        Log.d("pattttid",String.valueOf(idpat));
        User patient = holder.userDao.getUserById(idpat);
        Log.d("patient name",patient.getFullName());
        holder.apnDate.setText(String.valueOf(appointments.get(position).getDate()));
        holder.apnTime.setText(appointments.get(position).getTime());
        holder.apnPatName.setText(patient.getFullName());

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView apnDate,apnTime, apnPatName;
        UserDao userDao;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            AppDatabase db = Room.databaseBuilder(itemView.getContext(),
                    AppDatabase.class, "room_db").allowMainThreadQueries().build();
            userDao = db.userDao();

            apnDate=itemView.findViewById(R.id.AppintmentDate);
            apnTime=itemView.findViewById(R.id.AppointmentTime);
            apnPatName=itemView.findViewById(R.id.ApnDoctorName);
        }
    }
}
