package com.example.projectprm.DTO;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class RoleDTO {
    private int roleId;
    private String roleName;
    private boolean isDelete;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public RoleDTO() {
    }

    public RoleDTO(int roleId, String roleName, boolean isDelete) {

        this.roleId = roleId;
        this.roleName = roleName;
        this.isDelete = isDelete;
    }
}

