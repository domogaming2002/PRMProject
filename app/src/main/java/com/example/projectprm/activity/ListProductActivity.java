package com.example.projectprm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.projectprm.Converter.Converter;
import com.example.projectprm.DAO.CategoryDAO;
import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.CategoryDTO;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.Entity.Category;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.Entity.User;
import com.example.projectprm.R;
import com.example.projectprm.adapter.ProductListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    ArrayList<ProductDTO> productDTOS;
    ArrayList<CategoryDTO> categoryDTOS;
    AppDatabase db;
    ProductDAO productDAO;
    UserDAO userDAO;
    CategoryDAO categoryDAO;
    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    List<Product> productList;
    List<Category> categoryList;

    private void inItRoomDatabase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();
        userDAO = db.userDAO();
        categoryDAO = db.categoryDAO();

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
        productListAdapter = new ProductListAdapter(productDTOS, ListProductActivity.this, 1);
//        productListAdapter.setOnClickListener(this);
        GridLayoutManager gridProductList = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(productListAdapter);
        recyclerView.setLayoutManager(gridProductList);

        //Search By Name
        EditText searchProduct = findViewById(R.id.etSearchProduct);
        ImageView search = findViewById(R.id.btnSearchProduct);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDTOS.clear();
                for (Product product : productList) {
                    if(product.name.toLowerCase().contains(searchProduct.getText().toString().toLowerCase())){
                        productDTOS.add(new ProductDTO(product.productId, product.name, product.unitInStock, product.salePrice, product.oldPrice, product.image,
                                product.rating, product.description, product.categoryId, product.supplyId, product.isDelete));
                    }
                }
                productListAdapter.notifyDataSetChanged();
            }
        });

        //Search By Category
        // Khai báo danh sách String và Integer để lưu tên danh mục và ID tương ứng
        List<String> categoryNames = new ArrayList<>();
        List<Integer> categoryIds = new ArrayList<>();

        // Lấy danh sách các danh mục từ Room database và thêm vào danh sách String và Integer
        categoryList = categoryDAO.getListCategory();
        categoryNames.add("All");
        categoryIds.add(-1);
        for (Category category : categoryList) {
            categoryNames.add(category.name);
            categoryIds.add(category.categoryId);
        }

        // Tạo ArrayAdapter để hiển thị danh sách tên danh mục trong Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Thiết lập ArrayAdapter cho Spinner
        Spinner spinner = findViewById(R.id.category_spinner);
        spinner.setAdapter(adapter);

        // Thiết lập listener cho Spinner để lấy ID của danh mục được chọn
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int categoryId = categoryIds.get(position);
                if(categoryId == -1){
                    getData();
                }else{
                    productDTOS.clear();
                    productList = productDAO.findProductByCategoryId(categoryId);
                    for (Product product : productList) {
                        productDTOS.add(new ProductDTO(product.productId, product.name, product.unitInStock, product.salePrice, product.oldPrice, product.image,
                                product.rating, product.description, product.categoryId, product.supplyId, product.isDelete));
                    }
                    productListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        //
    }

    private void getData() {
        productDTOS.clear();
        productList = productDAO.getListProduct();
        for (Product product : productList) {
            //ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description, int categoryId, int supplyId, boolean isDelete)
            productDTOS.add(new ProductDTO(product.productId, product.name, product.unitInStock, product.salePrice, product.oldPrice, product.image,
                    product.rating, product.description, product.categoryId, product.supplyId, product.isDelete));
        }
        productListAdapter.notifyDataSetChanged();
    }

    private void createProduct() {
        //ProductDTO(int productId, String name, int unitInStock, double salePrice, String image, double rating, String description,
        // int categoryId, int supplyId, boolean isDelete)

        Product p = new Product();
        for (int i = 0; i < 10; i++) {
            p.name = "Product" + i;
            p.unitInStock = 1 + i;
            p.salePrice = 1 + i;
            p.oldPrice = 11 - i;
            p.image = "https://images2.thanhnien.vn/zoom/686_429/528068263637045248/2023/5/11/mau-tim-jennie-blackpink-5-1683770342157392395711-0-0-675-1080-crop-16837729036411840617379.jpg";
            p.rating = 5;
            p.description = "Product " + i + "is very good, bring me to love";
            p.categoryId = 1 + i;
            p.supplyId = 1 + i;
            p.isDelete = false;
            productDAO.insertAll(p);
        }

        // public CategoryDTO(int categoryId, String name, int parentId, String image, boolean isDelete) {
        Category c = new Category();
        for (int i = 0; i < 10; i++) {
            c.name = "Category " + i;
            c.parentId = 0;
            c.image = "1";
            c.isDelete = false;
            categoryDAO.insertCategory(c);
        }

        //public UserDTO(int userId, String email, String passwordHash, int roleId, String firstname, String lastname, boolean gender, String address, Date dob, String phoneNumber, String avatar, boolean isActive, boolean isDelete) {
        User u = new User();
        u.email = "test";
        u.passwordHash = "1";
        u.roleId = 1;
        u.firstname = "minh";
        u.lastname = "tran";
        u.gender = false;
        u.address = "So 1 ngo 372, Thuy Khue";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            u.dob = format.parse("2023-07-25");
        } catch (Exception e) {
            e.printStackTrace();
        }
        u.phoneNumber = "03572243473";
        u.avatar = "123";
        u.isActive = true;
        u.isDelete = false;
        userDAO.insertUser(u);
    }
}