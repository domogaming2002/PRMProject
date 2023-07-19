package com.example.projectprm.DTO;

public class ProductDTO { private int productId;
    private String name;
    private int unitInStock;
    private double salePrice;
    private String image;
    private double rating;
    private String description;
    private int categoryId;
    private CategoryDTO category;
    private int supplyId;
    private SupplierDTO supply;
    private boolean isDelete  = false;

    public ProductDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public SupplierDTO getSupply() {
        return supply;
    }

    public void setSupply(SupplierDTO supply) {
        this.supply = supply;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description, int categoryId, CategoryDTO category, int supplyId, SupplierDTO supply, boolean isDelete) {
        this.productId = productId;
        this.name = name;
        this.unitInStock = unitInStock;
        this.salePrice = salePrice;
        this.image = image;
        this.rating = rating;
        this.description = description;
        this.categoryId = categoryId;
        this.category = category;
        this.supplyId = supplyId;
        this.supply = supply;
        this.isDelete = isDelete;
    }

    public ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description, int categoryId, int supplyId, boolean isDelete) {
        this.productId = productId;
        this.name = name;
        this.unitInStock = unitInStock;
        this.salePrice = salePrice;
        this.image = image;
        this.rating = rating;
        this.description = description;
        this.categoryId = categoryId;
        this.supplyId = supplyId;
        this.isDelete = isDelete;
    }
}
