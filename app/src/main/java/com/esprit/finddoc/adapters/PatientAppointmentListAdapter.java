package com.esprit.finddoc.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PatientAppointmentListAdapter extends RecyclerView.Adapter<PatientAppointmentListAdapter.myviewholder>{
    List<Appointment> appointments;

    public PatientAppointmentListAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    @NonNull
    @NotNull
    @Override
    public PatientAppointmentListAdapter.myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_appointment_list_item,parent,false);


        return new PatientAppointmentListAdapter.myviewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull PatientAppointmentListAdapter.myviewholder holder, int position) {
        int iddoc = appointments.get(position).getDoctor_id();
        Log.d("docid",String.valueOf(iddoc));
        User doctor = holder.userDao.getUserById(iddoc);
        Log.d("doctor name",doctor.getFullName());
        holder.apnDate.setText(String.valueOf(appointments.get(position).getDate()));
        holder.apnTime.setText(appointments.get(position).getTime());
        holder.apnPatName.setText(doctor.getFullName());

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

            apnDate=itemView.findViewById(R.id.TVAppintmentDate);
            apnTime=itemView.findViewById(R.id.TVAppointmentTime);
            apnPatName=itemView.findViewById(R.id.TVApnDoctorName);
        }
    }
}
