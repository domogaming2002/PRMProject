package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.R;
import com.example.projectprm.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    ArrayList<ProductDTO> productDTOS;
    AppDatabase db;
    ProductDAO productDAO;
    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    List<Product> productList;

    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        productDTOS = new ArrayList<>();
        inItRoomDatabase();
        productList = productDAO.getListProduct();
//        createProduct();
        for (Product product : productList) {
            //ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description, int categoryId, int supplyId, boolean isDelete)
            productDTOS.add(new ProductDTO(product.productId, product.name, product.unitInStock, product.salePrice, product.oldPrice, product.image,
                    product.rating, product.description, product.categoryId, product.supplyId, product.isDelete));
        }

        recyclerView = findViewById(R.id.productListRecycleView);
        productListAdapter = new ProductListAdapter(productDTOS, ListProductActivity.this);
//        productListAdapter.setOnClickListener(this);
        GridLayoutManager gridProductList = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(productListAdapter);
        recyclerView.setLayoutManager(gridProductList);
    }

    private void createProduct() {
        //ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description,
        // int categoryId, int supplyId, boolean isDelete)

        Product p = new Product();
        for (int i = 0; i< 10; i++){
            p.name = "Product" + i;
            p.unitInStock = 1 + i;
            p.salePrice = 1 + i;
            p.image = "https://images.macrumors.com/t/9r84bU_ZTOTrUixxwhjHUFsAvD4=/800x0/smart/article-new/2017/09/iphonexdesign.jpg?lossy";
            p.rating = 5;
            p.description = "Product " + i + "is very good, bring me to love";
            p.categoryId = 1 +i;
            p.supplyId = 1 + i;
            p.isDelete = false;
            productDAO.insertAll(p);
        }

    }
}