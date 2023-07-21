package com.example.projectprm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.R;
import com.example.projectprm.activity.Update_Category;
import com.example.projectprm.activity.Update_Product;

import java.util.List;

public class ProductFragmentAdapter extends RecyclerView.Adapter<ProductFragmentAdapter.ProductHolder>{
    List<Product> products;
    private Context context;
    private ProductListener ProductListener;
    private ProductDAO productDAO;

    public ProductFragmentAdapter( Context context, List<Product> products, ProductDAO productDAO) {
        this.products = products;
        this.context = context;
        this.productDAO = productDAO;
    }

    public ProductFragmentAdapter(ProductFragmentAdapter.ProductListener productListener) {
        ProductListener = productListener;
    }

    public ProductListener getProductListener() {
        return ProductListener;
    }
    public Product getProductAt (int position) {
        return products.get(position);
    }

    public void setProductListener(ProductFragmentAdapter.ProductListener productListener) {
        ProductListener = productListener;
    }
    public void setList(List<Product> list) {
        this.products= list;
        notifyDataSetChanged(); // refesh
    }
    public ProductFragmentAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_fragment_item, parent, false);
        return new ProductHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final Product p= products.get(position);
        String Image = p.image;
        //Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        /*int restId = context.getResources().getIdentifier(Image, "drawable", context.getOpPackageName());
        holder.img.setImageResource(restId);*/
        holder.tv_name.setText(p.name);
        holder.tv_description.setText(p.description);
        holder.tv_price.setText(String.valueOf(p.salePrice));
        holder.tv_unitsInStock.setText(String.valueOf(p.unitInStock));

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Update_Product.class);
                intent.putExtra("productId", p.productId);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.deleteProductById(p.productId);
                products.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, products.size());
            }
        });

    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView tv_name, tv_description, tv_price, tv_unitsInStock;
        CardView layoutItem;
        Button updateBtn, deleteBtn;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imv_imgRV);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_unitsInStock = itemView.findViewById(R.id.tv_unitsInStock);
            layoutItem = itemView.findViewById(R.id.layout_itemRV);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (ProductListener != null) {
                ProductListener.onItemClick(view, getAdapterPosition());
            }
        }

    }
    public interface ProductListener {
        void onItemClick(View view, int position);

    }
}
