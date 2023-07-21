package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.Repository.OrderRepository;
import com.example.projectprm.adapter.OrderDetailItemAdapter;
import com.example.projectprm.util.OrderUtil;

import java.util.ArrayList;
import java.util.List;

public class UserOrderDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailItemAdapter orderDetailItemAdapter;
    OrderRepository orderRepository;
    OrderDTO currentOrder;

    TextView orderCodeTxt;
    TextView orderDateTxt;
    TextView orderStatusTxt;
    TextView orderCurrentStatusTxt;
    TextView orderCurrentStatusDateTxt;
    TextView orderCustomerName;
    TextView orderCustomerPhoneTxt;
    TextView customerAddressTxt;
    TextView supplierNameTxt;
    TextView orderAfterDiscount;
    TextView tempOrderMoney;
    TextView shipFeeTxt;
    TextView shipDiscount;
    ImageView userDetailBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        orderRepository = new OrderRepository(getApplicationContext());

        //hind action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setUpCartRecycleView();
        setUIViewBinding();
    }

    private void setUpCartRecycleView() {

        //Set up adapter
        orderDetailItemAdapter = new OrderDetailItemAdapter(convertOrderDetailToCartItem(), UserOrderDetailActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvOrderDetailList);
        recyclerView.setAdapter(orderDetailItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private List<CartItem> convertOrderDetailToCartItem() {
        List<CartItem> listCart = new ArrayList<>();
        for (OrderDetailDTO orderDetail : currentOrder.getOrderItemList()) {
            listCart.add(new CartItem(0, orderDetail.getProductDTO(), (int) orderDetail.getPrice(), orderDetail.getQuantity(), true));
        }
        return listCart;
    }

    private void setUIViewBinding() {
        orderCodeTxt = findViewById(R.id.orderCodeTxt);
        orderDateTxt = findViewById(R.id.orderDateTxt);
        orderStatusTxt = findViewById(R.id.orderStatusTxt);
        orderCurrentStatusTxt = findViewById(R.id.orderCurrentStatusTxt);
        orderCurrentStatusDateTxt = findViewById(R.id.orderCurrentStatusDateTxt);
        orderCustomerName = findViewById(R.id.orderCustomerName);
        orderCustomerPhoneTxt = findViewById(R.id.orderCustomerPhoneTxt);
        customerAddressTxt = findViewById(R.id.customerAddressTxt);
        supplierNameTxt = findViewById(R.id.supplierNameTxt);
        orderAfterDiscount = findViewById(R.id.orderAfterDiscount);
        tempOrderMoney = findViewById(R.id.tempOrderMoney);
        shipFeeTxt = findViewById(R.id.shipFee);
        shipDiscount = findViewById(R.id.shipDiscount);

        orderCodeTxt.setText(currentOrder.getOrderCode());
        orderDateTxt.setText(currentOrder.getOrderDate().toString());
        orderStatusTxt.setText(OrderUtil.getStatusStringByCode(currentOrder.getStatus()));
        orderCurrentStatusTxt.setText(OrderUtil.getStatusStringByCode(currentOrder.getStatus()));
        orderCurrentStatusDateTxt.setText(currentOrder.getShippedDate() != null
                ? currentOrder.getShippedDate().toString() : currentOrder.getOrderDate().toString());
        orderCustomerName.setText(currentOrder.getFullname());
        orderCustomerPhoneTxt.setText(currentOrder.getPhoneNumber());
        customerAddressTxt.setText(currentOrder.getAddress());

        setPriceLayout();
        userDetailBackBtn = findViewById(R.id.userDetailBackBtn);
        userDetailBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setPriceLayout() {
        int initTotal = countTotalMoney();
        tempOrderMoney.setText(String.valueOf(initTotal / 1000) + ".000 đ");

        int shipFee = OrderUtil.countFeeShip();
        shipFeeTxt.setText(String.valueOf(shipFee / 1000) + ".000 đ");

        int shipFeeDiscount = OrderUtil.countFeeShipDiscount();
        shipDiscount.setText(String.valueOf(shipFeeDiscount / 1000) + ".000 đ");

        int totalOrder = initTotal + shipFee - shipFeeDiscount;
        orderAfterDiscount.setText(String.valueOf(totalOrder / 1000) + ".000 đ");
    }

    private int countTotalMoney() {
        int total = 0;
        for (OrderDetailDTO item : currentOrder.getOrderItemList()) {
            total += item.getQuantity() * item.getPrice();
        }
        return total;
    }

    private void getOrderDetailFromIntent() {
        Bundle extra = getIntent().getExtras();
        int orderId = extra.getInt("orderId", 0);
//        int userId = extra.getInt("userId", 0);
//        User u = userDAO.getUserById(userId);
        currentOrder = orderRepository.getOrderById(orderId);
    }

}