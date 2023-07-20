package com.example.projectprm.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "unitInStock")
    public int unitInStock;

    @ColumnInfo(name = "salePrice")
    @Nullable
    public double salePrice;

    @ColumnInfo(name = "oldPrice")
    @Nullable
    public double oldPrice;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "rating")
    @Nullable
    public double rating;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "categoryId")
    public int categoryId;

    @ColumnInfo(name = "supplyId")
    public int supplyId;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete  = false;


}
