package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.Entity.Order;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.Repository.CartItemRepository;
import com.example.projectprm.Repository.OrderRepository;
import com.example.projectprm.Repository.UserRepository;
import com.example.projectprm.adapter.OrderDetailItemAdapter;
import com.example.projectprm.util.OrderUtil;
import com.example.projectprm.util.UserUtil;

import java.util.Date;
import java.util.List;

public class ProcessCheckoutActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailItemAdapter orderDetailItemAdapter;
    List<CartItem> cartItemList;
    CartItemRepository cartItemRepository;
    OrderRepository orderRepository;
    User loggedInUser;

    //UI ELEMENT
    TextView orderAfterDiscount1;
    TextView tempOrderMoney;
    TextView shipFeeTxt;
    TextView shipDiscount;
    TextView customerNameTxt;
    TextView customerPhoneTxt;
    TextView customerAddressTxt;
    TextView orderAfterDiscount2;
    Button orderBtn;
    ImageView icChangeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_order);
        loggedInUser = UserUtil.getLoggedInUser(getApplicationContext(), new UserRepository(getApplicationContext()));
        if(loggedInUser == null){
            Intent intent = new Intent(ProcessCheckoutActivity.this, Login.class);
            startActivity(intent);
        }

        cartItemRepository = new CartItemRepository();
        orderRepository = new OrderRepository(getApplicationContext());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setUpCartRecycleView();
        setUIViewBinding();
        setPriceLayout();
    }

    private void setUpCartRecycleView(){
        getBuyList();

        //Set up adapter
        orderDetailItemAdapter = new OrderDetailItemAdapter(cartItemList, ProcessCheckoutActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvBuyOrderList);
        recyclerView.setAdapter(orderDetailItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setUIViewBinding(){
        tempOrderMoney = findViewById(R.id.tempOrderMoney);
        shipFeeTxt = findViewById(R.id.shipFee);
        shipDiscount = findViewById(R.id.shipDiscount);
        orderAfterDiscount1 = findViewById(R.id.orderAfterDiscount);
        orderAfterDiscount2 = findViewById(R.id.orderAfterDiscount2);
        customerNameTxt = findViewById(R.id.customerNameTxt);
        customerNameTxt.setText(loggedInUser.firstname);
        customerPhoneTxt = findViewById(R.id.customerPhoneTxt);
        customerPhoneTxt.setText(loggedInUser.phoneNumber);
        customerAddressTxt = findViewById(R.id.customerAddressTxt);
        customerAddressTxt.setText(loggedInUser.address);

        orderBtn = findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
                Toast.makeText(ProcessCheckoutActivity.this, "Chúc mừng bạn đặt hàng thành công! Yay yay", Toast.LENGTH_SHORT).show();
            }
        });
        icChangeAddress = findViewById(R.id.icChangeAddress);
        icChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProcessCheckoutActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addOrder() {
        Order orderToAdd = new Order();
        orderToAdd.orderCode = OrderUtil.generateOrderCode();
        orderToAdd.orderDate =  new Date();
        orderToAdd.fullname = loggedInUser.firstname;
        orderToAdd.phoneNumber = loggedInUser.phoneNumber;
        orderToAdd.address = loggedInUser.address;
        orderToAdd.status = 1;
        orderToAdd.userId = loggedInUser.userId;

        orderRepository.addToOrder(orderToAdd, cartItemList);

        //go to order finish
        Intent intent = new Intent(ProcessCheckoutActivity.this, OrderFinishActivity.class);
        intent.putExtra("order", OrderUtil.orderToString(orderRepository.getRecentlyAddedOrder()));
        intent.putExtra("orderList", OrderUtil.orderListToString(cartItemList));
        startActivity(intent);
        finish();
    }

    private void setPriceLayout(){
        int initTotal = countTotalMoney();
        tempOrderMoney.setText(String.valueOf(initTotal/1000) + ".000 đ");

        int shipFee = OrderUtil.countFeeShip();
        shipFeeTxt.setText(String.valueOf(shipFee / 1000) + ".000 đ");

        int shipFeeDiscount = OrderUtil.countFeeShipDiscount();
        shipDiscount.setText(String.valueOf(shipFeeDiscount / 1000) + ".000 đ");

        int totalOrder = initTotal + shipFee - shipFeeDiscount;
        orderAfterDiscount1.setText(String.valueOf(totalOrder / 1000) + ".000 đ");
        orderAfterDiscount2.setText(String.valueOf(totalOrder / 1000) + ".000 đ");
    }

    private int countTotalMoney() {
        int total = 0;
        for (CartItem item : cartItemList) {
            if (item.isChecked()) {
                total += item.getProduct().getSalePrice();
            }
        }
        return total;
    }


    private void getBuyList(){
        cartItemList = cartItemRepository.getCartFromMemory(ProcessCheckoutActivity.this);
        for (int i = 0; i < cartItemList.size(); i++) {
            if(!cartItemList.get(i).isChecked()) {
                cartItemList.remove(i);
            }
        }
    }
}