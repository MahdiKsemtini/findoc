package com.esprit.finddoc.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.esprit.finddoc.Activities.Patient.DoctorDetailPatientActivity;
import com.esprit.finddoc.R;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.database.AppDatabase;
import com.esprit.finddoc.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminDoctorListAdapter extends RecyclerView.Adapter<AdminDoctorListAdapter.myviewholder>{
    List<User> users;

    public AdminDoctorListAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public AdminDoctorListAdapter.myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_doctor_list_item,parent,false);
        return new AdminDoctorListAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminDoctorListAdapter.myviewholder holder, int position) {
        holder.docName.setText(String.valueOf(users.get(position).getFullName()));
        holder.docEmail.setText(users.get(position).getEmail());
        holder.docAdress.setText(users.get(position).getAdress());
        int id = users.get(position).getId();

        if(users.get(position).getEtat()==true){
            holder.checketat.setVisibility(View.GONE);
        }

        else {
            holder.checketat.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AppDatabase db = Room.databaseBuilder(holder.checketat.getContext(),
                                    AppDatabase.class, "room_db").allowMainThreadQueries().build();
                            UserDao userDao = db.userDao();
                            userDao.updateById(id,true);
                            holder.checketat.setVisibility(View.GONE);

                        }
                    }
            );
        }




    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView docName,docEmail, docAdress;
        LinearLayout docItem;
        Button checketat;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            docName=itemView.findViewById(R.id.DoctorNameAdmin);
            docEmail=itemView.findViewById(R.id.DoctorEmailAdmin);
            docAdress=itemView.findViewById(R.id.DoctorAdressAdmin);
            docItem=itemView.findViewById(R.id.idDoctorItemAdmin);
            checketat=(Button) itemView.findViewById(R.id.checketatbutton);
        }
    }
}


