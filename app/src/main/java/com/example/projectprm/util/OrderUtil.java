package com.example.projectprm.util;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Random;

public class OrderUtil {

    public static int countFeeShip() {
        return 100000;
    }

    public static int countFeeShipDiscount() {
        return 100000;
    }

    public static String getStatusStringByCode(int status) {
        switch (status) {
            case 1:
                return Constants.ORDER_PAYMENT_STATUS_STRING;
            case 2:
                return Constants.ORDER_PROCESS_STATUS_STRING;
            case 3:
                return Constants.ORDER_DELIVERY_STATUS_STRING;
            case 4:
                return Constants.ORDER_SUCCESS_STATUS_STRING;
            case 5:
                return Constants.ORDER_CANCEL_STATUS_STRING;
        }
        return "";
    }

    public static int countTotalMoney(List<OrderDetail> orderDetails){
        int total = 0;
        for (OrderDetail orderDetail:orderDetails) {
            total += orderDetail.price * orderDetail.quantity;
        }
        return total;
    }

    public static String generateOrderCode() {
        Random random = new Random();
        int number = random.nextInt(900000000) + 100000000; // This will generate a random integer in range [100000000, 999999999]
        return Integer.toString(number);
    }

    public static String orderToString(Order order){
        Gson gson = new Gson();
        String json = gson.toJson(order);
        return json;
    }

    public static String orderListToString(List<CartItem> cartItems){
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        return json;
    }

    public static Order getOrderFromString(String orderString){
        Gson gson = new Gson();
        TypeToken<Order> token = new TypeToken<Order>() {};
        return gson.fromJson(orderString, token.getType());
    }

    public static List<CartItem> getOrderListFromString(String orderListString){
        Gson gson = new Gson();
        TypeToken<List<CartItem>> token = new TypeToken<List<CartItem>>() {};
        return gson.fromJson(orderListString, token.getType());
    }
}
