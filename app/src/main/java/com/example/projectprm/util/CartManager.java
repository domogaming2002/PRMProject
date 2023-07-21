package com.example.projectprm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectprm.DTO.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartManager {

    public static void saveCart(Context context, List<CartItem> cart) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(cart);

        prefs.edit().putString(Constants.CART_KEY, json).apply();
    }

    public static List<CartItem> loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.CART_KEY, null);

        if (json == null) {
            return new ArrayList<>();  // Return an empty cart if there's no saved cart
        } else {
            Gson gson = new Gson();
            TypeToken<List<CartItem>> token = new TypeToken<List<CartItem>>() {};
            return gson.fromJson(json, token.getType());
        }
    }

    public static void updateCart(Context context, List<CartItem> cart) {
        List<CartItem> cartItemList = loadCart(context);
        Iterator<CartItem> iterator = cartItemList.iterator();
        while (iterator.hasNext()) {
            CartItem cc = iterator.next();
            for (CartItem buyCart : cart) {
                if (buyCart.getProduct().getProductId() == cc.getProduct().getProductId()) {
                    iterator.remove(); // Sử dụng iterator để xóa phần tử an toàn
                    break; // Nếu tìm thấy và xóa, thoát khỏi vòng lặp for inner
                }y
            }
        }
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(cartItemList);

        prefs.edit().putString(Constants.CART_KEY, json).apply();
    }
}
