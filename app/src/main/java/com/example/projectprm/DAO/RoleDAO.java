package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.Role;

import java.util.List;

@Dao
public interface RoleDAO {
    @Query("Select * from Role where isDelete = 0")
    List<Role> getListRole();


    @Query("Select * from Role where roleId = :id and isDelete = 0")
    Role getRoleById(int id);

    @Insert
    void insertRole(Role... roles);

    @Update
    void updateRole(Role role);


}
