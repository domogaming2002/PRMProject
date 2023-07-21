package com.example.projectprm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Index;
import androidx.room.Room;

import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.util.Constants;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText emailTxt, passwordTxt;
    Button loginBtn, registerBtn;
    AppDatabase db;
    UserDAO userDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initRoomDatabase();
        SharedPreferences prefs = Login.this.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.USER_KEY, null);
        if (json != null) {
            Gson gson = new Gson();
            int userId = gson.fromJson(json, Integer.class);
            User userLogined = userDAO.getUserById(userId);
            Redirect(userLogined.roleId);
        }
        List<User> listUser = userDAO.getUserList();
        if (listUser.size() == 0) {
            User admin = new User();
            admin.roleId = 1;
            admin.email = "admin";
            admin.passwordHash = HashPassword("password");
            admin.firstname = "admin";
            userDAO.insertUser(admin);
        }
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    User user = userDAO.getUserLogin(emailTxt.getText().toString(), HashPassword(passwordTxt.getText().toString()));
                    if (user == null) {
                        Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences prefs = Login.this.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

                        Gson gson = new Gson();
                        String json = gson.toJson(user.userId);

                        prefs.edit().putString(Constants.USER_KEY, json).apply();
                        Redirect(user.roleId);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fill your email and password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public static String HashPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            // Chuyển mật khẩu thành mảng byte
            byte[] hash = md5Digest.digest(password.getBytes());
            // Chuyển mảng byte thành chuỗi hexa
            return new BigInteger(1, hash).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean validateInput() {
        if (emailTxt.getText().toString().isEmpty() || passwordTxt.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        userDAO = db.userDAO();

    }

    private void Redirect(int role){
        if(role == 1)
        {
            Intent intent = new Intent(Login.this, List_product_admin.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(Login.this, ListProductActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
