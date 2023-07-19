package com.example.projectprm.DTO;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class CategoryDTO{
    private int categoryId;
    private String name;
    private int parentId;
    private String image;
    private boolean isDelete;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryId, String name, int parentId, String image, boolean isDelete) {
        this.categoryId = categoryId;
        this.name = name;
        this.parentId = parentId;
        this.image = image;
        this.isDelete = isDelete;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

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
}
