package com.example.projectprm.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "email")
    public String email;

    @Nullable
    @ColumnInfo(name = "passwordHash")
    public String passwordHash;

    @ColumnInfo(name = "roleId")
    public int roleId;

    @Nullable
    @ColumnInfo(name = "firstname")
    public String firstname;

    @Nullable
    @ColumnInfo(name = "lastname")
    public String lastname;

    @Nullable
    @ColumnInfo(name = "gender")
    public boolean gender;

    @Nullable
    @ColumnInfo(name = "address")
    public String address;

    @Nullable
    @ColumnInfo(name = "dob")
    public Date dob;

    @Nullable
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;

    @Nullable
    @ColumnInfo(name = "avatar")
    public String avatar;

    @Nullable
    @ColumnInfo(name = "isActive")
    public boolean isActive;

    @Nullable
    @ColumnInfo(name = "isDelete")
    public boolean isDelete;
}
