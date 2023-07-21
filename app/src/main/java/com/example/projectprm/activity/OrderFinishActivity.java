package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.R;
import com.example.projectprm.adapter.OrderDetailItemAdapter;
import com.example.projectprm.util.OrderUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderFinishActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailItemAdapter orderDetailItemAdapter;

    Order successOrder;
    List<CartItem> cartItems;

    TextView finishOrderCodeTxt;
    TextView viewOrderLink;
    TextView totalMoneySuccessOrder;
    Button backToHomePageFromOrderSuccessBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finish);

        //hind action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getOrderInfoFromIntent();
        setUpCartRecycleView();
        setUIViewBinding();
    }

    private void setUIViewBinding() {
        finishOrderCodeTxt = findViewById(R.id.finishOrderCodeTxt);
        finishOrderCodeTxt.setText(successOrder.orderCode);
        viewOrderLink = findViewById(R.id.viewOrderLink);
        viewOrderLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderFinishActivity.this, UserOrderDetailActivity.class);
                intent.putExtra("orderId", successOrder.orderId);
                startActivity(intent);
            }
        });

        totalMoneySuccessOrder = findViewById(R.id.totalMoneySuccessOrder);
        totalMoneySuccessOrder.setText(String.valueOf(countTotalMoney()));

        backToHomePageFromOrderSuccessBtn = findViewById(R.id.backToHomePageFromOrderSuccessBtn);
        backToHomePageFromOrderSuccessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderFinishActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private int countTotalMoney() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isChecked()) {
                total += item.getProduct().getSalePrice();
            }
        }
        return total;
    }

    private void setUpCartRecycleView() {

        //Set up adapter
        orderDetailItemAdapter = new OrderDetailItemAdapter(cartItems, OrderFinishActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvOrderDetailList);
        recyclerView.setAdapter(orderDetailItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void getOrderInfoFromIntent() {
        Bundle extra = getIntent().getExtras();
        String orderJson = extra.getString("order", "");
        successOrder = OrderUtil.getOrderFromString(orderJson);
        String orderListJson = extra.getString("orderList", "");
        cartItems = OrderUtil.getOrderListFromString(orderListJson);
    }
}