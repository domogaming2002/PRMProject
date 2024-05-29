package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.Repository.CartItemRepository;
import com.example.projectprm.Repository.OrderRepository;
import com.example.projectprm.Repository.UserRepository;
import com.example.projectprm.adapter.CartItemAdapter;
import com.example.projectprm.adapter.OnOrderItemClickListener;
import com.example.projectprm.adapter.OrderItemAdapter;
import com.example.projectprm.util.OrderUtil;
import com.example.projectprm.util.UserUtil;

import java.util.List;

public class UserOrderListActivity extends AppCompatActivity implements OnOrderItemClickListener {
    RecyclerView recyclerView;
    OrderItemAdapter orderItemAdapter;
    List<OrderRecycle> orderRecycleList;
    OrderRepository orderRepository;

    User loggedInUser;
    ImageView backToHomePage2;
    EditText searchInput;
    ImageView searchOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_user);
        loggedInUser = UserUtil.getLoggedInUser(getApplicationContext(), new UserRepository(getApplicationContext()));
        if(loggedInUser == null){
            Intent intent = new Intent(UserOrderListActivity.this, Login.class);
            startActivity(intent);
        }
        //instantiate
        orderRepository = new OrderRepository(getApplicationContext());

        //hind action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        layoutViewBinding();
        setUpCartRecycleView();
        layoutViewBinding();
    }

    private void layoutViewBinding(){
        backToHomePage2 = findViewById(R.id.backToHomePage2);
        backToHomePage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchInput = findViewById(R.id.searchInput);
        searchOrderBtn = findViewById(R.id.searchOrderBtn);
        searchOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTxt = searchInput.getText().toString();
                for (int i = 0; i < orderRecycleList.size(); i++) {
                    if(!orderRecycleList.get(i).orderCode.contains(searchTxt)){
                        orderRecycleList.remove(i);
                    }
                }
                orderItemAdapter.notifyDataSetChanged();
            }
        });
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
        orderRecycleList = orderRepository.getOrdersByUser(loggedInUser.userId);
    }

    @Override
    public void onDetailBtnClick(int position) {
        Intent intent = new Intent(UserOrderListActivity.this, UserOrderDetailActivity.class);
        intent.putExtra("orderId", orderRecycleList.get(position).orderId);
        startActivity(intent);
    }

    @Override
    public void onWriteReviewBtnClick(int position) {

    }
}