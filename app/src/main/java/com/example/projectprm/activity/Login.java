package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {
    EditText emailTxt, passwordTxt;
    Button loginBtn, registerBtn;
    AppDatabase db;
    UserDAO userDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput()){
                    if(emailTxt.getText().toString() == "admin" && passwordTxt.getText().toString() == "password"){
                        Intent intent = new Intent(Login.this, List_supplier_admin.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        initRoomDatabase();
                        User user = userDAO.getUserLogin(emailTxt.getText().toString(), HashPassword(passwordTxt.getText().toString()));
                        if(user == null){
                            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
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

    private Boolean validateInput(){
        if(emailTxt.getText().toString().isEmpty() || passwordTxt.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        userDAO = db.userDAO();

    }
}
