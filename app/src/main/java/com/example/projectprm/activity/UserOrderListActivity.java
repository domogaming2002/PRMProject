package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.R;
import com.example.projectprm.Repository.CartItemRepository;
import com.example.projectprm.Repository.OrderRepository;
import com.example.projectprm.adapter.CartItemAdapter;
import com.example.projectprm.adapter.OnOrderItemClickListener;
import com.example.projectprm.adapter.OrderItemAdapter;

import java.util.List;

public class UserOrderListActivity extends AppCompatActivity implements OnOrderItemClickListener {
    RecyclerView recyclerView;
    OrderItemAdapter orderItemAdapter;
    List<OrderRecycle> orderRecycleList;
    OrderRepository orderRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_user);

        //instantiate
        orderRepository = new OrderRepository(getApplicationContext());

        //hind action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        layoutViewBinding();
    }

    private void layoutViewBinding(){
//        clickToBuy = findViewById(R.id.clickToBuy);
//        checkedAllItemCb = findViewById(R.id.checkedAllItem);
//        tempOrderMoney = findViewById(R.id.tempCountMoney);
//        clickToBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartActivity.this, ProcessCheckoutActivity.class);
//                startActivity(intent);
//            }
//        });
//        checkedAllItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                boolean checkValue = checkedAllItemCb.isChecked();
//                for (CartItem item : cartItemList) {
//                    item.setChecked(checkValue);
//                }
//                orderItemAdapter.notifyDataSetChanged();
//                orderRepository.setCartToMemory(CartActivity.this, cartItemList);
//                setTotalMoneyTxt();
//            }
//        });
    }

    private void setUpCartRecycleView() {
        getUserOrderList();

        //Set up adapter
        orderItemAdapter = new OrderItemAdapter(orderRecycleList, UserOrderListActivity.this);
        orderItemAdapter.setOnOrderItemClickListener(UserOrderListActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvUserOrderList);
        recyclerView.setAdapter(orderItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getUserOrderList() {
        int userId = 1;
        orderRecycleList = orderRepository.getOrdersByUser(userId);
    }

    @Override
    public void onDetailBtnClick(int position) {

    }

    @Override
    public void onWriteReviewBtnClick(int position) {

    }
}