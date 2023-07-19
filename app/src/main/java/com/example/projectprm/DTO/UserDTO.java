package com.example.projectprm.DTO;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;

public class UserDTO {
    private int userId;
    private String email;
    private String passwordHash;
    private int roleId;
    private String firstname;
    private String lastname;
    private boolean gender;
    private String address;
    private Date dob;
    private String phoneNumber;
    private String avatar;
    private boolean isActive;
    private boolean isDelete;

    public UserDTO() {
    }

    public UserDTO(int userId, String email, String passwordHash, int roleId, String firstname, String lastname, boolean gender, String address, Date dob, String phoneNumber, String avatar, boolean isActive, boolean isDelete) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.isActive = isActive;
        this.isDelete = isDelete;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
