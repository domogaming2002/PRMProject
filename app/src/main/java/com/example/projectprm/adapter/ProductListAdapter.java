package com.example.projectprm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.ProductDTO;
import com.example.projectprm.R;
import com.example.projectprm.activity.ProductDetail;
import com.example.projectprm.viewholder.ProductListViewHolder;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListViewHolder> {
    private List<ProductDTO> productDTOList;
    private Context context;

    public ProductListAdapter(List<ProductDTO> productDTOList, Context context) {
        this.productDTOList = productDTOList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productListView = inflater.inflate(R.layout.product_item, parent, false);
        ProductListViewHolder productListViewHolder = new ProductListViewHolder(productListView);
        return productListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ProductDTO productDTO = productDTOList.get(position);
        holder.productName.setText(productDTO.getName());
        holder.productNewPrice.setText(String.valueOf(productDTO.getSalePrice()));
        holder.productOldPrice.setText(String.valueOf(productDTO.getOldPrice()));
        Picasso.get().load(productDTO.getImage()).into(holder.productImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("productId",productDTO.getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDTOList.size();
    }
}
