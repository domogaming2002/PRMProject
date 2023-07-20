package com.example.projectprm.DTO;

public class CartItem {
    private int id;
    private ProductDTO product;
    private int quantity;
    private int price;
    private boolean checked;


    public CartItem() {

    }

    public CartItem(int id, ProductDTO product, int quantity, int price, boolean checked) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
