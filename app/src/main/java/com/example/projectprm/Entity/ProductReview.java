package com.example.projectprm.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.projectprm.Converter.Converter;

import java.util.Date;

@Entity
@TypeConverters(Converter.class)
public class ProductReview {
    @PrimaryKey(autoGenerate = true)
    public int reviewId;

    @ColumnInfo(name = "productId")
    public int productId;

    @ColumnInfo(name = "userId")
    public int userId;

    @ColumnInfo(name = "rating")
    public int rating;

    @Nullable
    @ColumnInfo(name = "title")
    public String title;

    @Nullable
    @ColumnInfo(name = "detail")
    public String detail;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete = false;

    @ColumnInfo(name = "createdDate")
    public Date createdDate;

}
