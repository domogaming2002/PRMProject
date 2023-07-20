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
import com.example.projectprm.R;
import com.example.projectprm.Repository.CartItemRepository;
import com.example.projectprm.adapter.OrderDetailItemAdapter;
import com.example.projectprm.util.OrderUtil;

import java.util.List;

public class ProcessCheckoutActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailItemAdapter orderDetailItemAdapter;
    List<CartItem> cartItemList;

    CartItemRepository cartItemRepository;
    TextView orderAfterDiscount1;
    TextView tempOrderMoney;
    TextView shipFeeTxt;
    TextView shipDiscount;
    TextView orderAfterDiscount2;
    Button orderBtn;
    ImageView icChangeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_order);
        cartItemRepository = new CartItemRepository();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setUpCartRecycleView();
        setPriceLayout();
    }

    private void setUpCartRecycleView(){
        getCartList();

        //Set up adapter
        orderDetailItemAdapter = new OrderDetailItemAdapter(cartItemList, ProcessCheckoutActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvBuyOrderList);
        recyclerView.setAdapter(orderDetailItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setPriceLayout(){
        int initTotal = countTotalMoney();
        tempOrderMoney = findViewById(R.id.tempOrderMoney);
        tempOrderMoney.setText(String.valueOf(initTotal));

        int shipFee = OrderUtil.countFeeShip();
        shipFeeTxt = findViewById(R.id.shipFee);
        shipFeeTxt.setText(String.valueOf(shipFee));

        int shipFeeDiscount = OrderUtil.countFeeShipDiscount();
        shipDiscount = findViewById(R.id.shipDiscount);
        shipDiscount.setText(String.valueOf(shipFeeDiscount));


        int totalOrder = initTotal + shipFee - shipFeeDiscount;
        orderAfterDiscount1 = findViewById(R.id.orderAfterDiscount);
        orderAfterDiscount2 = findViewById(R.id.orderAfterDiscount2);
        orderAfterDiscount1.setText(String.valueOf(totalOrder));
        orderAfterDiscount2.setText(String.valueOf(totalOrder));

        orderBtn = findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private int countTotalMoney() {
        int total = 0;
        for (CartItem item : cartItemList) {
            if (item.isChecked()) {
                total += item.getProduct().getSalePrice();
            }
        }
        return total;
    }


    private void getCartList(){
        cartItemList = cartItemRepository.getCartFromMemory(ProcessCheckoutActivity.this);
        for (CartItem cart: cartItemList) {
            if(!cart.isChecked()){
                cartItemList.remove(cart);
            }
        }
    }
}