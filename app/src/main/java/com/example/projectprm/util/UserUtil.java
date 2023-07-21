package com.example.projectprm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectprm.Entity.User;
import com.example.projectprm.Repository.UserRepository;
import com.google.gson.Gson;

public class UserUtil {

    public static User getLoggedInUser(Context context, UserRepository userRepository){
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.USER_KEY, null);
        Gson gson = new Gson();
        int userId = gson.fromJson(json, Integer.class);
        return userRepository.getUserById(userId);
    }
}
