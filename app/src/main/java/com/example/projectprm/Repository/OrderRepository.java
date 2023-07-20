package com.example.projectprm.Repository;

import android.content.Context;

import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DAO.OrderDetailDAO;
import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final Context context;
    AppDatabase db;
    ProductDAO productDAO;
    OrderDAO orderDAO;
    UserDAO userDAO;
    OrderDetailDAO orderDetailDAO;

    public OrderRepository(Context context) {
        this.context = context;
        initRoomDatabase();
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(context, AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();
        userDAO = db.userDAO();
        orderDAO = db.orderDAO();
        orderDetailDAO = db.orderDetailDAO();
    }

    public List<OrderRecycle> getOrdersByUser(int userId) {
        List<Order> orders = orderDAO.getOrderByUserId(userId);

        List<OrderRecycle>orderRecycleList = new ArrayList<>();
        for (Order order: orders) {
            OrderRecycle orderRecycle = new OrderRecycle();
            List<OrderDetail> orderDetails =  orderDetailDAO.getOrderDetailByOrderId(order.orderId);

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//            orderDetailDTO.setOrderId(or);
//            orderRecycle.representProduct = orderDetails.get(0);

        }

    }

}
