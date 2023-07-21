package com.example.projectprm.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("Select * from `Order` where isDelete = 0")
    List<Order> getListOrder();

    @Query("Select * from `Order` where orderId = :orderId and isDelete = 0")
    Order getOrderById(int orderId);

    @Query("Select * from `Order` where userId = :userId and isDelete = 0")
    List<Order> getOrderByUserId(int userId);

    @Query("Select * from `Order` where status = :status and isDelete = 0")
    List<Order> getOrderByStatus(String status);

    @Query("Select * from `Order` where userId = :userId and status = :status and isDelete = 0")
    List<Order> getOrderByUserIdAndStatus(int userId, String status);

    @Insert
    void insertOrder(Order... orders);

    @Update
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);
    @Query("SELECT * FROM `Order` ORDER BY orderId DESC LIMIT 1")
    Order getRecentlyAddedOrder();

}
