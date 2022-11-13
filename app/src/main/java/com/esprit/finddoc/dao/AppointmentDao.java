package com.esprit.finddoc.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

import java.util.List;

@Dao
public interface AppointmentDao {

    @Insert
    void insertrecord(Appointment appointment);

    @Query("SELECT EXISTS(SELECT * FROM Appointment WHERE date = :date AND time=:time AND doctor_id=:doctorid)")
    Boolean is_exist(String date,String time, int doctorid);

    @Query("SELECT * FROM Appointment WHERE doctor_id = :docId")
    List<Appointment> getAppointmentByDoctor(int docId);

    @Query("SELECT * FROM Appointment WHERE patient_id = :patId")
    List<Appointment> getAppointmentByPatient(int patId);

    @Query("DELETE FROM Appointment WHERE id = :id")
    void deleteById(int id);
}
