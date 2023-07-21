package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("Select * from User where isDelete = 0")
    List<User> getUserList();

    @Query("Select * from User where userId = :id and isDelete = 0")
    User getUserById(int id);

    @Insert
    void insertUser(User users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from User where email = :email and passwordHash = :passwordHash")
    User getUserLogin(String email, String passwordHash);

}
