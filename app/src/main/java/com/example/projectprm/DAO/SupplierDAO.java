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

    @Query("Select * from Supplier where name like  '%' || :pName || '%' and isDelete = 0")
    List<Supplier> findSupplierByName(String pName);

    @Query("Update Supplier SET isDelete = 1 Where supplyId = :sId")
    void deleteSupplierById(int sId);

    @Query("Select supplyId from Supplier where name = :cName")
    int getSupplyIdByName(String cName);

}
