package com.example.projectprm.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;


@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int orderId;

    @ColumnInfo(name = "userId")
    public int userId;

    @ColumnInfo(name = "orderCode")
    public String orderCode;

    @ColumnInfo(name = "orderDate")
    public Date orderDate;

    @Nullable
    @ColumnInfo(name = "shippedDate")
    public Date shippedDate;

    @Nullable
    @ColumnInfo(name = "fullname")
    public String fullname;

    @Nullable
    @ColumnInfo(name = "email")
    public String email;

    @Nullable
    @ColumnInfo(name = "address")
    public String address;

    @Nullable
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete;
}
