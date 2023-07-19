package com.example.projectprm.DTO;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;

public class ProductReviewDTO {
    private int reviewId;

    private int productId;

    private int userId;

    private int rating;

    private String title;

    private String detail;

    private boolean isDelete = false;

    private Date createdDate;

    public ProductReviewDTO() {
    }

    public ProductReviewDTO(int reviewId, int productId, int userId, int rating, String title, String detail, boolean isDelete, Date createdDate) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.title = title;
        this.detail = detail;
        this.isDelete = isDelete;
        this.createdDate = createdDate;
    }
}
