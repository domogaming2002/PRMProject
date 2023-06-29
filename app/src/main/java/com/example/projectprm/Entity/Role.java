package com.example.projectprm.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Role {

    @PrimaryKey(autoGenerate = true)
    public int roleId;

    @ColumnInfo(name = "roleName")
    public String roleName;

    @Nullable
    @ColumnInfo(name = "isDelete")
    public boolean isDelete;
}
