package com.example.projectprm.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    public int categoryId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "parentId")
    public int parentId;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "isDelete")
    public boolean isDelete;
}
