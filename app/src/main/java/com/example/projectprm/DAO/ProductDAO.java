package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("Select * from Product where isDelete = 0")
    List<Product> getListProduct();

    @Query("Select * from Product where productId = :id and isDelete = 0")
    Product findProductById(int id);

    @Query("Select * from Product where name like  '%' || :pName || '%' and isDelete = 0")
    List<Product> findProductByName(String pName);

    @Query("Select * from Product where categoryId = :cateId and isDelete = 0")
    List<Product> findProductByCategoryId(int cateId);

    @Insert
    void insertAll(Product... products);

    @Update
    void updateProduct(Product product);


    //Khong dung`, delete bang cach update isDelete = 1
    @Delete
    void deleteProduct(Product product);

    @Query("Update Product SET isDelete = 1 Where productId = :sId")
    void deleteProductById(int sId);

}
