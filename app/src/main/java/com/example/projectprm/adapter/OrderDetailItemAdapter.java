package com.example.projectprm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.OrderDetailViewHolder> {
    private List<CartItem> cartItems;
    private Context mContext;

    public OrderDetailItemAdapter(List<CartItem> cartItems, Context mContext){
        this.cartItems = cartItems;
        this.mContext = mContext;
    }

    @Override
    public OrderDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.order_detail_item, parent, false);
        OrderDetailViewHolder viewHolder = new OrderDetailViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderDetailViewHolder holder, int position) {
        CartItem cart = cartItems.get(position);
        holder.productName.setText(cart.getProduct().getName());
        holder.quantity.setText(String.valueOf(cart.getQuantity()));
        holder.price.setText(String.valueOf(cart.getPrice() / 1000) + ".000 đ");
        Picasso.get().load(cart.getProduct().getImage()).into(holder.image);
//        int drawableId = mContext.getResources().getIdentifier(cart.getProduct().getImage(), "drawable", mContext.getPackageName());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView quantity;
        public TextView price;

        public ImageView image;

        public OrderDetailViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.tvProductOrderDetailName);
            quantity = itemView.findViewById(R.id.quantity);
            image = itemView.findViewById(R.id.imageOrderDetailProduct);
            price = itemView.findViewById(R.id.itemPrice);
        }
    }
}
