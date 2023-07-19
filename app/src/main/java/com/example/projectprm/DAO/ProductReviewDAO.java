package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.ProductReview;

import java.util.List;

@Dao
public interface ProductReviewDAO {
    @Query("Select * from ProductReview where isDelete = 0")
    List<ProductReview> getListProductReview();

    @Insert
    void insertProductReview(ProductReview... productReviews);

    @Update
    void updateProductReview(ProductReview productReview);

    @Delete
    void deleteProductReview(ProductReview productReview);


}
