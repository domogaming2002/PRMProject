package com.example.projectprm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectprm.DAO.SupplierDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Supplier;
import com.example.projectprm.R;
import com.example.projectprm.fragments.SupplierFragment;

public class Update_Supplier extends AppCompatActivity {
    AppDatabase db;
    SupplierDAO supplierDAO;
    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM").allowMainThreadQueries().build();
        supplierDAO = db.supplierDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("supplierId", 0);
        initRoomDatabase();
        Supplier supplier = supplierDAO.getSupplierById(id);
        EditText nameTxt = findViewById(R.id.edt_addName);
        EditText emailTxt = findViewById(R.id.edt_addEmail);
        EditText phoneTxt = findViewById(R.id.edt_addPhone);
        EditText addressTxt = findViewById(R.id.edt_addAddress);
        Button addButton = findViewById(R.id.btn_addProduct);
        addButton.setText("Update");
        Button backButton = findViewById(R.id.btn_cancelAddProduct);
        TextView pageName = findViewById(R.id.pageName);
        pageName.setText("Update Supplier");

        nameTxt.setText(supplier.name);
        emailTxt.setText(supplier.email);
        phoneTxt.setText(supplier.phone);
        addressTxt.setText(supplier.address);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Supplier supplier1 = new Supplier();
                supplier1.supplyId = id;
                supplier1.name = nameTxt.getText().toString();
                supplier1.phone = phoneTxt.getText().toString();
                supplier1.email = emailTxt.getText().toString();
                supplier1.address = addressTxt.getText().toString();
                supplierDAO.updateSupplier(supplier1);
                Intent intent = new Intent(Update_Supplier.this, List_supplier_admin.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_Supplier.this, List_supplier_admin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}