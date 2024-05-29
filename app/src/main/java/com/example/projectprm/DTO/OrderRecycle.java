package com.example.projectprm.DTO;

import com.example.projectprm.Entity.OrderDetail;

import java.util.Date;
import java.util.List;

public class OrderRecycle {
    public int orderId;
    public String orderCode;
    public OrderDetailDTO representProduct = new OrderDetailDTO();
    public String status;
    public int totalMoney;
    public int numberOfItems;
}
