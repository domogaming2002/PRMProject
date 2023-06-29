package com.example.projectprm.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int productId;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "UnityInStock")
    public int unityInStock;

    @ColumnInfo(name = "SalePrice")
    public double salePrice;

    @ColumnInfo(name = "Image")
    public String image;
}
