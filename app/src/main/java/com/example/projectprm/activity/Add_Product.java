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

public class Add_Product extends AppCompatActivity {
    AppDatabase db;
    ProductDAO productDAO;
    CategoryDAO categoryDAO;
    SupplierDAO supplierDAO;
    Spinner spinner_category, spinner_supplier;
    int id_cate;
    int id_sup;

    List<Category> categories;
    List<Supplier> suppliers;
    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM").allowMainThreadQueries().build();
        productDAO = db.productDAO();
        categoryDAO = db.categoryDAO();
        supplierDAO = db.supplierDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_layout);
        EditText nameTxt = findViewById(R.id.edt_addname);
        EditText descriptionTxt = findViewById(R.id.edt_adddescription);
        EditText oldPriceTxt = findViewById(R.id.edt_addOldPrice);
        EditText salePriceTxt = findViewById(R.id.edt_addSalePrice);
        EditText unitsInStockTxt = findViewById(R.id.edt_addUnitsInStock);
        EditText imageTxt = findViewById(R.id.edt_addImage);
        spinner_supplier = findViewById(R.id.spinner_supplier);
        spinner_category = findViewById(R.id.spinner_category);
        initRoomDatabase();
        categories = categoryDAO.getListCategory();
        List<String> listNameCategory= new ArrayList<String>();
        for(Category c : categories){
            listNameCategory.add(c.name);
        }
        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item,listNameCategory);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(arrayAdapter);
        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                id_cate= categoryDAO.getCategoryIdByName(listNameCategory.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Add_Product.this,"You need select Category",Toast.LENGTH_LONG).show();
            }
        } );


        suppliers = supplierDAO.getListSupplier();
        List<String> listNameSupplier= new ArrayList<String>();
        for(Supplier c : suppliers){
            listNameSupplier.add(c.name);
        }
        ArrayAdapter arrayAdapter2= new ArrayAdapter(this, android.R.layout.simple_spinner_item,listNameSupplier);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_supplier.setAdapter(arrayAdapter2);
        spinner_supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                id_sup= supplierDAO.getSupplyIdByName(listNameSupplier.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Add_Product.this,"You need select Supplier",Toast.LENGTH_LONG).show();
            }
        } );




        Button addButton = findViewById(R.id.btn_addProduct);
        addButton.setText("Add");
        Button backButton = findViewById(R.id.btn_cancelAddProduct);
        TextView pageName = findViewById(R.id.tv_addproduct);
        pageName.setText("Add Product");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.name = nameTxt.getText().toString();
                product.description = descriptionTxt.getText().toString();
                product.categoryId = id_cate;
                product.supplyId = id_sup;
                product.oldPrice = Double.parseDouble(oldPriceTxt.getText().toString());
                product.salePrice = Double.parseDouble(salePriceTxt.getText().toString());
                product.unitInStock = Integer.parseInt(unitsInStockTxt.getText().toString());
                product.image = imageTxt.getText().toString();
                productDAO.insertAll(product);
                Intent intent = new Intent(Add_Product.this, List_product_admin.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Product.this, List_product_admin.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
