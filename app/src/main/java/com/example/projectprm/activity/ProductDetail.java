package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.util.CartManager;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class ProductDetail extends AppCompatActivity {

    AppDatabase db;
    ProductDAO productDAO;
    UserDAO userDAO;
    ImageView image;
    TextView productName, productSalePrice, productDiscount, address, productDescription;
    Button addToCart;
    Product product;

    private void initRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();
        userDAO = db.userDAO();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initRoomDatabase();
        Bundle extra = getIntent().getExtras();
        int productId = extra.getInt("productId", 0);
        int userId = extra.getInt("userId", 0);
        User u = userDAO.getUserById(userId);

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
        productDiscount.setText("-" + formatter.format(discount) + "%");

        address = findViewById(R.id.userAddress);
        address.setText(u.address);

        productDescription = findViewById(R.id.productDetailDescription);
        productDescription.setText(product.description);

        addToCart = findViewById(R.id.btnAddProductToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart();
            }
        });
    }

    private boolean checkItemExist(List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId() == product.productId) {
                return true;
            }
        }
        return false;
    }

    private void addProductToCart() {
        List<CartItem> cartItems = CartManager.loadCart(ProductDetail.this);

        //Sản phẩm đã tồn tại
        if (checkItemExist(cartItems)) {
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getProduct().getProductId() == product.productId) {
                    int productQuantity = cartItems.get(i).getProduct().getUnitInStock();
                    int cartQuantity = cartItems.get(i).getQuantity();

                    //nếu không còn hàng!
                    if (cartQuantity >= productQuantity) {
                        Toast.makeText(ProductDetail.this, "Đã hết hàng!", Toast.LENGTH_SHORT).show();
                        return;
                    } else { //nếu còn hàng -- update quantity
                        Toast.makeText(ProductDetail.this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                        cartItems.get(i).setQuantity(cartQuantity + 1);
                    }
                }
            }
        } else { //nếu chưa tồn tại
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.productId);
            productDTO.setCategoryId(product.categoryId);
            productDTO.setImage(product.image);
            productDTO.setDescription(product.description);
            productDTO.setDelete(product.isDelete);
            productDTO.setName(product.name);
            productDTO.setOldPrice(product.oldPrice);
            productDTO.setSalePrice(product.salePrice);
            productDTO.setRating(product.rating);
            productDTO.setUnitInStock(product.unitInStock);
            productDTO.setSupplyId(product.supplyId);

            cartItems.add(new CartItem(0, productDTO, 1, (int) product.salePrice, false));
        }

        //Lưu vào bộ nhớ
        CartManager.saveCart(ProductDetail.this, cartItems);
    }
}