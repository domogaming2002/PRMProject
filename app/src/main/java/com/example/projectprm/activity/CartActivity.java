package com.example.projectprm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.R;
import com.example.projectprm.Repository.CartItemRepository;
import com.example.projectprm.adapter.CartItemAdapter;
import com.example.projectprm.adapter.OnCartItemClickListener;

import java.util.List;

public class CartActivity extends AppCompatActivity implements OnCartItemClickListener {

    RecyclerView recyclerView;
    CartItemAdapter cartItemAdapter;
    List<CartItem> cartItemList;

    CartItemRepository cartItemRepository;
    Button clickToBuy;
    TextView tempOrderMoney;
    CheckBox checkedAllItemCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);
        clickToBuy = findViewById(R.id.clickToBuy);
        checkedAllItemCb = findViewById(R.id.checkedAllItem);
        tempOrderMoney = findViewById(R.id.tempCountMoney);
        clickToBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProcessCheckoutActivity.class);
                startActivity(intent);
            }
        });
        checkedAllItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checkValue = checkedAllItemCb.isChecked();
                for (CartItem item : cartItemList) {
                    item.setChecked(checkValue);
                }
                cartItemAdapter.notifyDataSetChanged();
                cartItemRepository.setCartToMemory(CartActivity.this, cartItemList);
                countTotalMoney();
            }
        });

        cartItemRepository = new CartItemRepository();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

//        cartItemRepository.setCartToMemory(CartActivity.this, cartItemRepository.getTestCartList());

        //PRODUCT ADAPTER
        setUpCartRecycleView();

        //
        countTotalMoney();
    }

    private void setUpCartRecycleView() {
        getCartList();

        //Set up adapter
        cartItemAdapter = new CartItemAdapter(cartItemList, CartActivity.this);
        cartItemAdapter.setOnCartItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //SET recycleView
        recyclerView = findViewById(R.id.rvCartList);
        recyclerView.setAdapter(cartItemAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void countTotalMoney() {
        int total = 0;
        for (CartItem item : cartItemList) {
            if (item.isChecked()) {
                total += item.getProduct().getSalePrice();
            }
        }
        tempOrderMoney.setText(String.valueOf(total));
    }

    private void getCartList() {
        cartItemList = cartItemRepository.getCartFromMemory(CartActivity.this);
    }

    @Override
    public void onMinusBtnClick(int position) {
        int currentQuantity = cartItemList.get(position).getQuantity();
        if (currentQuantity <= 1) {
            cartItemList.remove(position);
        } else {
            cartItemList.get(position).setQuantity(currentQuantity - 1);
        }
        cartItemAdapter.notifyDataSetChanged();
        cartItemRepository.setCartToMemory(CartActivity.this, cartItemList);
        countTotalMoney();
    }

    @Override
    public void onPlusBtnClick(int position) {
        int currentQuantity = cartItemList.get(position).getQuantity();
        if (currentQuantity >= cartItemList.get(position).getProduct().getUnitInStock()) {
            Toast.makeText(this, "Sản phẩm còn lại không đủ!", Toast.LENGTH_SHORT).show();
            return;
        }
        cartItemList.get(position).setQuantity(currentQuantity + 1);
        cartItemAdapter.notifyDataSetChanged();
        //Lưu vào bộ nhớ
        cartItemRepository.setCartToMemory(CartActivity.this, cartItemList);
        countTotalMoney();
    }

    @Override
    public void onCheckToBuyChange(int position, boolean isCheck) {
        cartItemList.get(position).setChecked(isCheck);
        countTotalMoney();
        cartItemRepository.setCartToMemory(CartActivity.this, cartItemList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_context, menu);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Toast.makeText(this, "Sản phẩm còn lại không đủ!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}