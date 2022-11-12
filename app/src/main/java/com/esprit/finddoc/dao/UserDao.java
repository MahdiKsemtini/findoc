package com.esprit.finddoc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.esprit.finddoc.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertrecord(User users);

    @Query("SELECT EXISTS(SELECT * FROM User WHERE email = :userEmail)")
    Boolean is_exist(String userEmail);

    @Query("SELECT * FROM User WHERE email = :userEmail")
    User getUserByEmail(String userEmail);


    @Query("SELECT * FROM User")
    List<User> getallusers();

    @Query("SELECT * FROM User WHERE type='Doctor'")
    List<User> getAllDoctors();

    @Query("DELETE FROM User WHERE id = :id")
    void deleteById(int id);

    @Query("UPDATE User SET full_name = :fname, email=:lname WHERE id = :id")
    void updateById(int id, String fname, String lname);
}
