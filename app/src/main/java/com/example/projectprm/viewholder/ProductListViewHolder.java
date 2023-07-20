package com.example.projectprm.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;


public class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ImageView productImage;
    public TextView productName, productOldPrice, productNewPrice;


    public ProductListViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.imageProductListRecycleView);
        productName = itemView.findViewById(R.id.productNameProductListRecycleView);
        productOldPrice = itemView.findViewById(R.id.productOldPriceProductListRecycleView);
        productNewPrice = itemView.findViewById(R.id.productSalePriceProductListRecycleView);
    }
}
