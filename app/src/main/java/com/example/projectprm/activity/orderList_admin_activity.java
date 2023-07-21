package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.R;
import com.example.projectprm.adapter.OrderListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class orderList_admin_activity extends AppCompatActivity {
    ArrayList<OrderDTO> orderDTOS;
    AppDatabase db;
    OrderDAO orderDAO;
    RecyclerView recyclerView;
    OrderListAdapter orderListAdapter;
    List<Order> orderList;

    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        orderDAO = db.orderDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_admin);
        Toolbar toolbar = findViewById(R.id.toolbarorder);
        setSupportActionBar(toolbar);

        orderDTOS = new ArrayList<>();
        inItRoomDatabase();
        createOrder();
        orderList = orderDAO.getListOrder();

        for (Order order : orderList) {
            orderDTOS.add(new OrderDTO(order.orderId, order.userId, order.orderCode, order.orderDate, order.shippedDate, order.fullname,
                    order.email, order.address, order.phoneNumber, order.status, order.isDelete));
        }

        recyclerView = findViewById(R.id.rvCart);
        orderListAdapter = new OrderListAdapter(orderDTOS, this);
        orderListAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int orderId) {
                Intent intent = new Intent(orderList_admin_activity.this, OrderDetailAdminActivity.class);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
            }
        });

        GridLayoutManager gridOrderList = new GridLayoutManager(this, 1);
        recyclerView.setAdapter(orderListAdapter);
        recyclerView.setLayoutManager(gridOrderList);
    }

    private void createOrder() {
        Order o = new Order();
        String dateString = "2023-07-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i< 10; i++){
            o.orderCode = "Order" + i;
            o.userId = 1 + i;
            o.status = 0;
            try {
                o.orderDate = dateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            o.isDelete = false;
            orderDAO.insertOrder(o);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.menuProduct) {
            Intent productIntent = new Intent(this, List_product_admin.class);
            startActivity(productIntent);
        } else if (itemId == R.id.menuCategory) {
            Intent productIntent = new Intent(this, List_category_admin.class);
            startActivity(productIntent);
        } else if (itemId == R.id.menuTotalOrder) {
            Intent orderIntent = new Intent(this, orderList_admin_activity.class);
            startActivity(orderIntent);
        } else if (itemId == R.id.menuSupplier) {
            Intent productIntent = new Intent(this, List_supplier_admin.class);
            startActivity(productIntent);
        } else if (itemId == R.id.menuLogOut) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        // TODO: Thực hiện đăng xuất
        // Ví dụ: Chuyển đến màn hình đăng nhập và xóa thông tin đăng nhập
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
        finish();
    }
}