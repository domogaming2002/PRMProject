package com.example.projectprm.Repository;

import android.content.Context;

import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.User;

public class UserRepository {
    private final Context context;
    AppDatabase db;
    UserDAO userDAO;

    OrderDetailRepository orderDetailRepository;

    public UserRepository(Context context) {
        this.context = context;
        initRoomDatabase();
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(context, AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        userDAO = db.userDAO();
    }

    public User getUserById(int userId){
        return userDAO.getUserById(userId);
    }

}
