package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectprm.DAO.CategoryDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Category;
import com.example.projectprm.R;

public class Update_Category extends AppCompatActivity {
    AppDatabase db;
    CategoryDAO categoryDAO;
    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM").allowMainThreadQueries().build();
        categoryDAO = db.categoryDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("categoryId", 0);
        initRoomDatabase();
        Category category = categoryDAO.getCategoryById(id);
        EditText nameTxt = findViewById(R.id.edt_addName);
        EditText imageTxt = findViewById(R.id.edt_addimage);
        Button addButton = findViewById(R.id.btn_addCategory);
        addButton.setText("Update");
        Button backButton = findViewById(R.id.btn_cancelAddCategory);
        TextView pageName = findViewById(R.id.pageName);
        pageName.setText("Update Category");

        nameTxt.setText(category.name);
        imageTxt.setText(category.image);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category1 = new Category();
                category1.categoryId = id;
                category1.name = nameTxt.getText().toString();
                category1.image = imageTxt.getText().toString();
                categoryDAO.updateCategory(category1);
                Intent intent = new Intent(Update_Category.this, List_category_admin.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_Category.this, List_category_admin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}