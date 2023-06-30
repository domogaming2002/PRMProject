package com.example.projectprm.DTO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projectprm.Entity.Category;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.OrderDetail;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.Entity.ProductReview;
import com.example.projectprm.Entity.Role;
import com.example.projectprm.Entity.Supplier;
import com.example.projectprm.Entity.User;

@Database(entities = {Product.class, Category.class, Order.class, OrderDetail.class, ProductReview.class,
Role.class, Supplier.class, User.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

}
