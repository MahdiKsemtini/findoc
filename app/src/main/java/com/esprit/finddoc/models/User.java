package com.esprit.finddoc.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


// below line is for setting table name.
@Entity
public class User  {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "adresse")
    private String adress;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;


    public User(String fullName, String type, String adress, String email, String password) {
        this.fullName = fullName;
        this.type = type;
        this.adress = adress;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}



