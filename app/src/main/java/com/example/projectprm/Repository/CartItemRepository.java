package com.example.projectprm.Repository;

import android.content.Context;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.util.CartManager;

import java.util.ArrayList;
import java.util.List;

public class CartItemRepository {

    public List<CartItem> getCartFromMemory(Context context) {
        return CartManager.loadCart(context);
    }

    public void setCartToMemory(Context context, List<CartItem> cart) {
        CartManager.saveCart(context, cart);
    }

    public List<CartItem> getTestCartList(){
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(0, new ProductDTO(1, "Chuyện con mèo ăn trộm sách", 20, 95000, 120000, "iphone11",
        5, "Sách này không hay đâu. Đừng đọc nhé các bạn ơi", 1, 1, false), 1, 95000, false));
        cartItems.add(new CartItem(1, new ProductDTO(2, "Chuyện con mèo ăn trộm sách", 20, 80000, 120000, "iphone11",
        5, "Sách này không hay đâu. Đừng đọc nhé các bạn ơi", 1, 1, false), 1, 80000, false));
        cartItems.add(new CartItem(2, new ProductDTO(3, "Chuyện con mèo ăn trộm sách", 20, 70000, 120000, "iphone11",
        5, "Sách này không hay đâu. Đừng đọc nhé các bạn ơi", 1, 1, false), 1, 70000, false));
        cartItems.add(new CartItem(3, new ProductDTO(4, "Chuyện con mèo ăn trộm sách", 20, 95000, 120000, "iphone11",
        5, "Sách này không hay đâu. Đừng đọc nhé các bạn ơi", 1, 1, false), 1, 95000, false));
        return cartItems;
    }
}
