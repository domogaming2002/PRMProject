package com.example.projectprm.Repository;

import android.content.Context;

import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DAO.OrderDetailDAO;
import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.SupplierDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.OrderDetail;
import com.example.projectprm.util.OrderUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final Context context;
    AppDatabase db;
    OrderDAO orderDAO;

    OrderDetailRepository orderDetailRepository;

    public OrderRepository(Context context) {
        this.context = context;
        initRoomDatabase();
        orderDetailRepository = new OrderDetailRepository(context);
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(context, AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        orderDAO = db.orderDAO();
    }

    public OrderDTO getOrderById(int orderId){
        Order order = orderDAO.getOrderById(orderId);
        return getOrderDetailListWithNestedObject(order);
    }

    public List<OrderRecycle> getOrdersByUser(int userId) {
        List<Order> orders = orderDAO.getOrderByUserId(userId);

        List<OrderRecycle> orderRecycleList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByOrderId(order.orderId);
            if (!orderDetails.isEmpty()) {
                OrderDetail firstOrderDetail = orderDetails.get(0);

                OrderRecycle orderRecycle = new OrderRecycle();
                orderRecycle.orderId = order.orderId;
                orderRecycle.orderCode = order.orderCode;
                orderRecycle.representProduct = orderDetailRepository.getOrderDetailDTOWithNestedObject(firstOrderDetail);
                orderRecycle.numberOfItems = orderDetails.size();
                orderRecycle.totalMoney = OrderUtil.countTotalMoney(orderDetails);
                orderRecycle.status = OrderUtil.getStatusStringByCode(order.status);
                orderRecycleList.add(orderRecycle);
            }
        }
        return orderRecycleList;
    }

    public List<OrderRecycle> getOrdersAll() {
        List<Order> orders = orderDAO.getListOrder();

        List<OrderRecycle> orderRecycleList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByOrderId(order.orderId);
            if (!orderDetails.isEmpty()) {
                OrderDetail firstOrderDetail = orderDetails.get(0);

                OrderRecycle orderRecycle = new OrderRecycle();
                orderRecycle.orderId = order.orderId;
                orderRecycle.orderCode = order.orderCode;
                orderRecycle.representProduct = orderDetailRepository.getOrderDetailDTOWithNestedObject(firstOrderDetail);
                orderRecycle.numberOfItems = orderDetails.size();
                orderRecycle.totalMoney = OrderUtil.countTotalMoney(orderDetails);
                orderRecycle.status = OrderUtil.getStatusStringByCode(order.status);
                orderRecycleList.add(orderRecycle);
            }
        }
        return orderRecycleList;
    }

    private OrderDTO getOrderDetailListWithNestedObject(Order order){
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailByOrderId(order.orderId);
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        for (OrderDetail od: orderDetails) {
            orderDetailDTOs.add(orderDetailRepository.getOrderDetailDTOWithNestedObject(od));
        }

        orderDTO.setOrderItemList(orderDetailDTOs);
        orderDTO.setOrderCode(order.orderCode);
        orderDTO.setOrderDate(order.orderDate);
        orderDTO.setOrderId(order.orderId);
        orderDTO.setOrderDate(order.orderDate);
        orderDTO.setAddress(order.address);
        orderDTO.setEmail(order.email);
        orderDTO.setFullname(order.fullname);
        orderDTO.setPhoneNumber(order.phoneNumber);
        orderDTO.setShippedDate(order.shippedDate);
        orderDTO.setStatus(order.status);
        orderDTO.setUserId(order.userId);
        return orderDTO;
    }

    public void addToOrder(Order orderToAdd, List<CartItem> cartItemList){
        orderDAO.insertOrder(orderToAdd);

        Order addedOrder = getRecentlyAddedOrder();

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem item: cartItemList) {
            OrderDetail od = new OrderDetail();
            od.orderId = addedOrder.orderId;
            od.productId = item.getProduct().getProductId();
            od.quantity = item.getQuantity();
            od.price = item.getPrice();
            od.isDelete = false;
            orderDetails.add(od);
        }

        orderDetailRepository.insertAllOrderDetail(orderDetails);
    }

    public Order getRecentlyAddedOrder(){
        return orderDAO.getRecentlyAddedOrder();
    }

}
