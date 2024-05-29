package com.example.projectprm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.R;
import com.example.projectprm.Repository.OrderRepository;
import com.example.projectprm.adapter.OnOrderItemClickListener;
import com.example.projectprm.adapter.OrderItemAdapter;
import com.example.projectprm.adapter.OrderListAdapter;
import com.example.projectprm.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class orderList_admin_activity extends AppCompatActivity implements OnOrderItemClickListener {
    RecyclerView recyclerView;
    OrderItemAdapter orderItemAdapter;
    List<OrderRecycle> orderRecycleList;
    OrderRepository orderRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_admin);

        //instantiate
        orderRepository = new OrderRepository(getApplicationContext());

        //hind action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setUpCartRecycleView();
    }


    private void setUpCartRecycleView() {
        getUserOrderList();

        //Set up adapter
        orderItemAdapter = new OrderItemAdapter(orderRecycleList, orderList_admin_activity.this);
        orderItemAdapter.setOnOrderItemClickListener(orderList_admin_activity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvCart);
        recyclerView.setAdapter(orderItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getUserOrderList() {
        orderRecycleList = orderRepository.getOrdersAll();
    }


//    private void createOrder() {
//        Order o = new Order();
//        String dateString = "2023-07-20";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i< 10; i++){
//            o.orderCode = "Order" + i;
//            o.userId = 1 + i;
//            o.status = 0;
//            try {
//                o.orderDate = dateFormat.parse(dateString);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            o.isDelete = false;
//            orderDAO.insertOrder(o);
//        }
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
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
        SharedPreferences prefs = this.getSharedPreferences(Constants.PREFS_NAME, this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(Constants.USER_KEY);
        editor.apply();
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onDetailBtnClick(int position) {
        Intent intent = new Intent(orderList_admin_activity.this, OrderDetailAdminActivity.class);
        intent.putExtra("orderId", orderRecycleList.get(position).orderId);
        startActivity(intent);
    }

    @Override
    public void onWriteReviewBtnClick(int position) {

    }
}