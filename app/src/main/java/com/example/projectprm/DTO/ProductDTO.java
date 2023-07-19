package com.example.projectprm.DTO;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.projectprm.Entity.Category;
import com.example.projectprm.Entity.Supplier;

public class ProductDTO { private int productId;
    private String name;
    private int unitInStock;
    private double salePrice;
    private String image;
    private double rating;
    private String description;
    private int categoryId;
    private Category category;
    private int supplyId;
    private Supplier supply;
    private boolean isDelete  = false;

    public ProductDTO() {
    }


}
