package com.esprit.finddoc.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.esprit.finddoc.dao.AppointmentDao;
import com.esprit.finddoc.dao.UserDao;
import com.esprit.finddoc.models.Appointment;
import com.esprit.finddoc.models.User;

@Database(entities = {User.class, Appointment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract AppointmentDao appointmentDao();
}

