package com.example.projectprm.activity;

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

public class Register extends AppCompatActivity {
    EditText email, password, rePassword, fullName;
    Button registerBtn;
    AppDatabase db;
    UserDAO userDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);
        rePassword = findViewById(R.id.rePasswordTxt);
        fullName = findViewById(R.id.fullNameTxt);
        registerBtn = findViewById(R.id.signUpBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userRegister = new User();
                userRegister.email = email.getText().toString();
                userRegister.passwordHash = HashPassword(password.getText().toString());
                userRegister.firstname = fullName.getText().toString();
                if(validateInput(userRegister)){
                    //Insert to database
                    initRoomDatabase();
                    userRegister.roleId = 2;
                    userDAO.insertUser(userRegister);
                    Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Fill all fields or check your password!", Toast.LENGTH_SHORT).show();
                }
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

    private Boolean validateInput(User user){
        if(user.firstname.isEmpty() || password.getText().toString() != rePassword.getText().toString()
        || password.getText().toString().isEmpty() || user.email.isEmpty() ){
            return true;
        }
        return false;
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        userDAO = db.userDAO();

    }
}
