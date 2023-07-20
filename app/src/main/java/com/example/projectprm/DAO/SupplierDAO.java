package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.Supplier;

import java.util.List;

@Dao
public interface SupplierDAO {

    @Query("Select * from Supplier where isDelete = 0")
    List<Supplier> getListSupplier();

    @Query("Select * from Supplier where supplyId = :id and isDelete = 0")
    Supplier getSupplierById(int id);

    @Insert
    void insertSupplier(Supplier... suppliers);

    @Update
    void updateSupplier(Supplier supplier);

    @Delete
    void deleteSupplier(Supplier supplier);



}
