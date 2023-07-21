package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectprm.DAO.CategoryDAO;
import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.SupplierDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Category;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.Entity.Supplier;
import com.example.projectprm.R;

import java.util.ArrayList;
import java.util.List;

public class Update_Product extends AppCompatActivity {
    AppDatabase db;
    ProductDAO productDAO;

    int id_cate;
    int id_sup;


    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM").allowMainThreadQueries().build();
        productDAO = db.productDAO();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_layout);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("productId", 0);
        initRoomDatabase();
        Product product = productDAO.findProductById(id);
        EditText nameTxt = findViewById(R.id.edt_addname);
        EditText descriptionTxt = findViewById(R.id.edt_adddescription);
        EditText oldPriceTxt = findViewById(R.id.edt_addOldPrice);
        EditText salePriceTxt = findViewById(R.id.edt_addSalePrice);
        EditText unitsInStockTxt = findViewById(R.id.edt_addUnitsInStock);




        Button addButton = findViewById(R.id.btn_addProduct);
        addButton.setText("Update");
        Button backButton = findViewById(R.id.btn_cancelAddProduct);

        nameTxt.setText(product.name);
        descriptionTxt.setText(product.description);
        oldPriceTxt.setText(String.valueOf(product.oldPrice));
        salePriceTxt.setText(String.valueOf(product.salePrice));
        unitsInStockTxt.setText(String.valueOf(product.unitInStock));



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product1 = new Product();
                product1.productId = id;
                product1.name = nameTxt.getText().toString();
                product1.description = descriptionTxt.getText().toString();
                product1.categoryId = product.categoryId;
                product1.supplyId = product.supplyId;
                product1.oldPrice = Double.parseDouble(oldPriceTxt.getText().toString());
                product1.salePrice = Double.parseDouble(salePriceTxt.getText().toString());
                product1.unitInStock = Integer.parseInt(unitsInStockTxt.getText().toString());
                product1.image = "Walle";
                productDAO.updateProduct(product1);
                Intent intent = new Intent(Update_Product.this, List_product_admin.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_Product.this, List_product_admin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
