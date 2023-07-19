package com.example.projectprm.DTO;

import androidx.room.ColumnInfo;

public class OrderDetailDTO {

    private int orderId;

    private int productId;

    private int quantity;

    private double price;

    private boolean isDelete ;

    private OrderDTO orderDTO;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderId, int productId, int quantity, double price, boolean isDelete, OrderDTO orderDTO) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.isDelete = isDelete;
        this.orderDTO = orderDTO;
    }

    public OrderDetailDTO(int orderId, int productId, int quantity, double price, boolean isDelete) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.isDelete = isDelete;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
