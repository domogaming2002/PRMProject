package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.R;
import com.example.projectprm.adapter.ProductFragmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class List_product_admin extends AppCompatActivity implements ProductFragmentAdapter.ProductListener {

    RecyclerView recyclerView;
    ProductFragmentAdapter adapter;
    AppDatabase db;
    ProductDAO productDAO;
    List<Product> list;
    FloatingActionButton fab;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);
        Toolbar toolbar = findViewById(R.id.toolbarproduct);
        setSupportActionBar(toolbar);

        initRoomDatabase();
        fab = findViewById(R.id.fab_Product);
        recyclerView = findViewById(R.id.productRecyclerView);
        searchView = findViewById(R.id.sv_product);

        list = new ArrayList<Product>();
        list = productDAO.getListProduct();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_product_admin.this, Add_Product.class);
                startActivity(intent);
            }
        });
        adapter = new ProductFragmentAdapter(this, list, productDAO);
        adapter.setProductListener(List_product_admin.this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Product> list = productDAO.findProductByName(s);
                adapter.setList(list);
                return true;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        // Xử lý sự kiện khi item trong RecyclerView được click
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(this, AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();
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