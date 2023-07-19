package com.example.projectprm.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectprm.Entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {

    @Query("Select * from OrderDetail where isDelete = 0")
    List<OrderDetailDAO> getOrderDetailList();


    @Query("Select * from OrderDetail where orderId = :orderId and isDelete = 0")
    List<OrderDetailDAO> getOrderDetailByOrderId(int orderId);

    @Insert
    void insertOrderDetail(OrderDetail... orderDetails);

    @Update
    void updateOrderDetail(OrderDetail orderDetail);

    @Delete
    void deleteOrderDetail(OrderDetail orderDetail);

}
