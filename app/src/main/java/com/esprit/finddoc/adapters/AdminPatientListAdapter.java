package com.esprit.finddoc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.finddoc.R;
import com.esprit.finddoc.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminPatientListAdapter extends RecyclerView.Adapter<AdminPatientListAdapter.myviewholder>{
    List<User> users;

    public AdminPatientListAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public AdminPatientListAdapter.myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_patient_list_item,parent,false);
        return new AdminPatientListAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminPatientListAdapter.myviewholder holder, int position) {
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
        LinearLayout docItem;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            docName=itemView.findViewById(R.id.PatientNameAdmin);
            docEmail=itemView.findViewById(R.id.PatientEmailAdmin);
            docAdress=itemView.findViewById(R.id.PatientAdressAdmin);
            docItem=itemView.findViewById(R.id.idPatientItemAdmin);
        }
    }
}


