package com.example.projectprm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.CartItem;
import com.example.projectprm.R;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private Context mContext;
    private OnCartItemClickListener listener;

    public void setOnCartItemClickListener(OnCartItemClickListener listener) {
        this.listener = listener;
    }
    public CartItemAdapter(List<CartItem> cartItems, Context mContext){
        this.cartItems = cartItems;
        this.mContext = mContext;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.cart_item, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(cartView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem cart = cartItems.get(position);
        holder.productName.setText(cart.getProduct().getName());
        holder.quantity.setText(String.valueOf(cart.getQuantity()));
        holder.oldPrice.setText(getDisplayPrice(cart.getProduct().getOldPrice()));
        holder.salePrice.setText(getDisplayPrice(cart.getProduct().getSalePrice()));
        holder.itemChooseCb.setChecked(cart.isChecked());
        int drawableId = mContext.getResources().getIdentifier(cart.getProduct().getImage(), "drawable", mContext.getPackageName());
        holder.image.setImageResource(drawableId);

//        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onMinusBtnClick(position);
//                }
//            }
//        });
//
//        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onPlusBtnClick(position);
//                }
//            }
//        });
    }

    private String getDisplayPrice(double price){
        return String.valueOf(price / 1000) + "00 đ";
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView quantity;
        public TextView oldPrice;
        public TextView salePrice;
        public TextView btnMinus;
        public TextView btnPlus;
        public CheckBox itemChooseCb;

        public ImageView image;

        public CartViewHolder(View itemView, OnCartItemClickListener listener) {
            super(itemView);

            itemChooseCb = itemView.findViewById(R.id.checkedItem);
            productName = itemView.findViewById(R.id.tvProductCartName);
            quantity = itemView.findViewById(R.id.tvCartProductQuantity);
            image = itemView.findViewById(R.id.imageCartProduct);
            salePrice = itemView.findViewById(R.id.promotionPrice);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onMinusBtnClick(position);
                    }
                }
            });
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onPlusBtnClick(position);
                    }
                }
            });

            itemChooseCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onCheckToBuyChange(position, isChecked);
                    }
                }
            });
        }
    }
}
