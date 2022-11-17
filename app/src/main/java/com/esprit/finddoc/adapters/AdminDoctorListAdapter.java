package com.esprit.finddoc.adapters;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        String receiver = users.get(position).getEmail();

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

                            new MyTask().execute(receiver);





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
        ImageView checketat;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            docName=itemView.findViewById(R.id.DoctorNameAdmin);
            docEmail=itemView.findViewById(R.id.DoctorEmailAdmin);
            docAdress=itemView.findViewById(R.id.DoctorAdressAdmin);
            checketat=(ImageView) itemView.findViewById(R.id.checketatbutton);
        }
    }

    private class MyTask
            extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params)
        {
            String tomail = params[0];
            MailjetClient client;
            MailjetRequest request = null;
            MailjetResponse response = null;
            client = new MailjetClient("9323b7c932604769ab4019db162a3372", "a6554f436a043cb193241329f05c9fd2", new ClientOptions("v3.1"));
            try {
                request = new MailjetRequest(Emailv31.resource)
                        .property(Emailv31.MESSAGES, new JSONArray()
                                .put(new JSONObject()
                                        .put(Emailv31.Message.FROM, new JSONObject()
                                                .put("Email", "alaaabdessalem.moalla@esprit.tn")
                                                .put("Name", "FinDoc"))
                                        .put(Emailv31.Message.TO, new JSONArray()
                                                .put(new JSONObject()
                                                        .put("Email", tomail)
                                                        .put("Name", "Doctor")))
                                        .put(Emailv31.Message.SUBJECT, "Verification mail.")
                                        .put(Emailv31.Message.TEXTPART, "Greetings from FinDoc")
                                        .put(Emailv31.Message.HTMLPART, "<h3>Dear Doctor, welcome to FinDoc!</h3><br />Your account has been verified!")
                                        .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                response = client.post(request);
            } catch (MailjetException e) {
                e.printStackTrace();
            } catch (MailjetSocketTimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            return tomail;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            // do something with the result
        }
    }
}


