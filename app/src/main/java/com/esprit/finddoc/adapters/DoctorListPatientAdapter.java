package com.esprit.finddoc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.finddoc.R;
import com.esprit.finddoc.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DoctorListPatientAdapter extends RecyclerView.Adapter<DoctorListPatientAdapter.myviewholder> {
    List<User> users;

    public DoctorListPatientAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_list_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorListPatientAdapter.myviewholder holder, int position) {
        holder.docName.setText(String.valueOf(users.get(position).getFullName()));
        holder.docEmail.setText(users.get(position).getEmail());
        holder.docAdress.setText(users.get(position).getAdress());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView docName,docEmail, docAdress;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            docName=itemView.findViewById(R.id.DoctorName);
            docEmail=itemView.findViewById(R.id.DoctorEmail);
            docAdress=itemView.findViewById(R.id.DoctorAdress);
        }
    }
}

