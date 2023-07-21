package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.util.Constants;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    AppDatabase db;
    UserDAO userDAO;

    EditText uName, uAddress, uPhone;


    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        userDAO = db.userDAO();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        inItRoomDatabase();

        //Tao spinner
        // Tạo một ArrayAdapter để hiển thị danh sách lựa chọn
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, new String[]{"Nam", "Nữ"}
        );

        // Định dạng lại tên gọi Nam/Nữ tương ứng với giá trị true/false
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinnerGender);
        spinner.setAdapter(genderAdapter);
        //

        SharedPreferences prefs = ProfileActivity.this.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.USER_KEY, null);
        Gson gson = new Gson();
        int userId = gson.fromJson(json, Integer.class);
        User u = userDAO.getUserById(userId);

        uName = findViewById(R.id.profileUserName);
        uAddress = findViewById(R.id.profileUserAddress);
        uPhone = findViewById(R.id.profilePhone);

        uName.setText(u.firstname);
        uAddress.setText(u.address);
        uPhone.setText(u.phoneNumber);
        if (u.gender) {
            spinner.setSelection(0); // Nam
        } else {
            spinner.setSelection(1); // Nữ
        }

        //Calender
        DatePicker datePicker = findViewById(R.id.profileDob);

        // Tạo đối tượng Calendar và thiết lập giá trị cho nó
        if(u.dob != null){

            Calendar cal = Calendar.getInstance();
            cal.setTime(u.dob);
            datePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }

        Button btn = findViewById(R.id.btnUpdateProfile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userUpdate = userDAO.getUserById(userId);
                if( uName.getText().toString().trim().isEmpty() || uAddress.getText().toString().trim().isEmpty()
                        || uPhone.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Update Profile Fail. Not Allow null" , Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                userUpdate.firstname = uName.getText().toString();
                userUpdate.address = uAddress.getText().toString();
                userUpdate.phoneNumber = uPhone.getText().toString();
                int selectedPosition = spinner.getSelectedItemPosition();
                if(selectedPosition == 0){
                    userUpdate.gender = true;
                }else{
                    userUpdate.gender = false;
                }

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                userUpdate.dob = cal.getTime();
                userDAO.updateUser(userUpdate);
                Toast.makeText(getApplicationContext(), "Update Profile Successfully" , Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }


    //Menu Main Demo
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_cart == item.getItemId()) {
            Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
            startActivity(intent);
            return true;
        }

        if (R.id.action_logout == item.getItemId()) {
            SharedPreferences prefs = this.getSharedPreferences(Constants.PREFS_NAME, this.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(Constants.USER_KEY);
            editor.apply();
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
            finish();
        }

        if (R.id.action_profile == item.getItemId()) {
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        if (R.id.action_Product == item.getItemId()) {
            Intent intent = new Intent(ProfileActivity.this, ListProductActivity.class);
            startActivity(intent);
            return true;
        }

        return false;

    }
}