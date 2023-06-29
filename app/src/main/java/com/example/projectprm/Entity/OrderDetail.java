package com.example.projectprm.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity( primaryKeys = {"orderId", "productId"})
public class OrderDetail {

    @ColumnInfo(name = "orderId")
    public int orderId;

    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete;

}
