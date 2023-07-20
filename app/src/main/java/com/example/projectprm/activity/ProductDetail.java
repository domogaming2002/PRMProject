package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

public class ProductDetail extends AppCompatActivity {

    AppDatabase db;
    ProductDAO productDAO;
    ImageView image;
    TextView productName, productSalePrice,productDiscount, address, productDescription;
    Button addToCart;
    Product product;
    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        inItRoomDatabase();
        Bundle extra = getIntent().getExtras();
        int productId = extra.getInt("productId",0);
        product = productDAO.findProductById(productId);
        image = findViewById(R.id.productDetailImage);
        Picasso.get().load(product.image).into(image);
        productName = findViewById(R.id.productDetailName);
        productName.setText(product.name);
        productSalePrice = findViewById(R.id.productDetailSalePrice);
        productSalePrice.setText(product.salePrice + "");
        double discount = (1 - (product.salePrice / product.oldPrice)) * 100;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(0);
        productDiscount = findViewById(R.id.productDetailDiscount);
        productDiscount.setText("-" +formatter.format(discount ) + "%");

        address = findViewById(R.id.userAddress);

        productDescription = findViewById(R.id.productDetailDescription);
        productDescription.setText(product.description);

        addToCart = findViewById(R.id.btnAddProductToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}