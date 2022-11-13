package com.esprit.finddoc.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.finddoc.Activities.Patient.DoctorDetailPatientActivity;
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
        Intent intent=new Intent(new Intent(holder.docItem.getContext(), DoctorDetailPatientActivity.class));
        intent.putExtra("username",String.valueOf(users.get(position).getFullName()));
        intent.putExtra("useremail",String.valueOf(users.get(position).getEmail()));
        intent.putExtra("useradress",String.valueOf(users.get(position).getAdress()));
        intent.putExtra("userid",users.get(position).getId());

        holder.docItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.docItem.getContext().startActivity(intent);
            }
        });


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

            docName=itemView.findViewById(R.id.DoctorName);
            docEmail=itemView.findViewById(R.id.DoctorEmail);
            docAdress=itemView.findViewById(R.id.DoctorAdress);
            docItem=itemView.findViewById(R.id.idDoctorItem);
        }
    }
}

