package com.example.projectprm.DTO;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class SupplierDTO { private int supplyId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private boolean isDelete  = false;

    public SupplierDTO(int supplyId, String name, String email, String phone, String address, boolean isDelete) {
        this.supplyId = supplyId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isDelete = isDelete;
    }

    public SupplierDTO() {

    }

    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
