package com.example.projectprm.DTO;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;

public class ProductReviewDTO {
    private int reviewId;

    private int productId;
    private ProductDTO product;

    private int userId;

    private int rating;

    private String title;

    private String detail;

    private boolean isDelete = false;

    private Date createdDate;

    public ProductReviewDTO() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public ProductReviewDTO(int reviewId, int productId, ProductDTO product, int userId, int rating, String title, String detail, boolean isDelete, Date createdDate) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.product = product;
        this.userId = userId;
        this.rating = rating;
        this.title = title;
        this.detail = detail;
        this.isDelete = isDelete;
        this.createdDate = createdDate;
    }
}
