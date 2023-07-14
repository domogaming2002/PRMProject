package com.example.projectprm.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Category {

    private String name;

    private int parentId;

    private String image;

    private boolean isDelete  = false;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Category(String name, int parentId, String image, boolean isDelete) {
        this.name = name;
        this.parentId = parentId;
        this.image = image;
        this.isDelete = isDelete;
    }
}
