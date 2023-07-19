package com.example.projectprm.DTO;

import java.util.Date;

public class OrderDTO {
    private int orderId;

    private int userId;

    private String orderCode;

    private Date orderDate;

    private Date shippedDate;

    private String fullname;

    private String email;

    private String address;

    private String phoneNumber;

    private int status;

    private boolean isDelete;

    private ProductDTO productDTO;

    public OrderDTO() {

    }

    public OrderDTO(int orderId, int userId, String orderCode, Date orderDate, Date shippedDate, String fullname, String email, String address, String phoneNumber, int status, boolean isDelete, ProductDTO productDTO) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.isDelete = isDelete;
        this.productDTO = productDTO;
    }

    public OrderDTO(int orderId, int userId, String orderCode, Date orderDate, Date shippedDate, String fullname, String email, String address, String phoneNumber, int status, boolean isDelete) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.isDelete = isDelete;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
