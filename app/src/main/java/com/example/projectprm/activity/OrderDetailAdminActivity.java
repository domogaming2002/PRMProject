package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.R;

public class OrderDetailAdminActivity extends AppCompatActivity {
    OrderDAO orderDAO;
    AppDatabase db;
    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        orderDAO = db.orderDAO();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inItRoomDatabase();
        setContentView(R.layout.activity_order_detail_admin);
        int orderId = getIntent().getIntExtra("orderId",0);
        Order order = orderDAO.getOrderById(orderId);
        TextView orderCode = findViewById(R.id.orderCodeTxt);
        TextView status = findViewById(R.id.orderCurrentStatusTxt);
        TextView statusDate = findViewById(R.id.orderCurrentStatusDateTxt);
        TextView address = findViewById(R.id.customerAddressTxt);
        TextView phone = findViewById(R.id.orderCustomerPhoneTxt);
        TextView name = findViewById(R.id.orderCustomerName);
        orderCode.setText(order.orderCode);
        address.setText(order.address);
        phone.setText(order.phoneNumber);
        name.setText(order.fullname);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.status = 1;
                orderDAO.updateOrder(order);
                status.setText("Đã thanh toán");
            }
        });
    }
}