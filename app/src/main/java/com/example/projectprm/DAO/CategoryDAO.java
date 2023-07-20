package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("Select * from Category where isDelete = 0")
    List<Category> getListCategory();

    @Query("Select * from category where categoryId = :id and isDelete = 0")
    Category getCategoryById(int id);

    @Insert
    void insertCategory(Category... categories);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);
}
