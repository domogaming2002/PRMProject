package com.example.projectprm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.projectprm.DAO.CategoryDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Category;
import com.example.projectprm.R;
import com.example.projectprm.adapter.CategoryFragmentAdapter;
import com.example.projectprm.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class List_category_admin extends AppCompatActivity implements CategoryFragmentAdapter.CategoryListener {

    RecyclerView recyclerView;
    CategoryFragmentAdapter adapter;
    AppDatabase db;
    CategoryDAO categoryDAO;
    List<Category> list;
    FloatingActionButton fab;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_category);
//        Toolbar toolbar = findViewById(R.id.toolbarcategory);

//        setSupportActionBar(toolbar);

        initRoomDatabase();
        fab = findViewById(R.id.fab_Category);
        recyclerView = findViewById(R.id.recycleViewCategory);
        searchView = findViewById(R.id.sv_category);

        list = new ArrayList<Category>();
        list = categoryDAO.getListCategory();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_category_admin.this, Add_Category.class);
                startActivity(intent);
            }
        });
        adapter = new CategoryFragmentAdapter( List_category_admin.this, list, categoryDAO);
        adapter.setCategoryListener(List_category_admin.this);
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
                List<Category> list = categoryDAO.findCategoryByName(s);
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
        categoryDAO = db.categoryDAO();
    }

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
}