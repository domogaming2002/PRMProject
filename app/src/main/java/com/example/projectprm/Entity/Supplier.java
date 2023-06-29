package com.example.projectprm.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Supplier {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "supplyId")
    public int supplyId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete  = false;
}
