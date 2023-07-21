package com.example.projectprm.Repository;

import android.content.Context;

import androidx.room.Room;

import com.example.projectprm.DAO.OrderDAO;
import com.example.projectprm.DAO.OrderDetailDAO;
import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DAO.SupplierDAO;
import com.example.projectprm.DAO.UserDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.DTO.OrderDetailDTO;
import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.DTO.SupplierDTO;
import com.example.projectprm.Entity.OrderDetail;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.Entity.Supplier;

import java.util.List;

public class OrderDetailRepository {
    private final Context context;
    AppDatabase db;
    ProductDAO productDAO;
    OrderDAO orderDAO;
    UserDAO userDAO;
    SupplierDAO supplierDAO;
    OrderDetailDAO orderDetailDAO;

    OrderDetailRepository orderDetailRepository;

    public OrderDetailRepository(Context context) {
        this.context = context;
        initRoomDatabase();
    }

    private void initRoomDatabase() {
        db = Room.databaseBuilder(context, AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();
        userDAO = db.userDAO();
        supplierDAO = db.supplierDAO();
        orderDetailDAO = db.orderDetailDAO();
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        return orderDetailDAO.getOrderDetailByOrderId(orderId);
    }

    public OrderDetailDTO getOrderDetailDTOWithNestedObject(OrderDetail od) {
        Product product = productDAO.findProductById(od.productId);

        Supplier supplier = supplierDAO.getSupplierById(product.supplyId);
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplyId(supplier.supplyId);
        supplierDTO.setName(supplier.name);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.productId);
        productDTO.setName(product.name);
        productDTO.setSalePrice(product.salePrice);
        productDTO.setImage(product.image);
        productDTO.setSupply(new SupplierDTO());

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setOrderId(od.orderId);
        orderDetailDTO.setProductDTO(productDTO);
        orderDetailDTO.setQuantity(od.quantity);
        orderDetailDTO.setPrice(od.price);

        return orderDetailDTO;
    }

    public void insertAllOrderDetail(List<OrderDetail> orderDetails) {
        for (OrderDetail od : orderDetails) {
            orderDetailDAO.insertOrderDetail(od);
        }
    }
}
