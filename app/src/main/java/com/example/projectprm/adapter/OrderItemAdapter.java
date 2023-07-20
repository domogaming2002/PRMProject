package com.example.projectprm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.OrderRecycle;
import com.example.projectprm.R;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    private List<OrderRecycle> orderRecycles;
    private Context mContext;
    private OnOrderItemClickListener listener;

    public void setOnOrderItemClickListener(OnOrderItemClickListener listener) {
        this.listener = listener;
    }

    public OrderItemAdapter(List<OrderRecycle> orderRecycles, Context mContext) {
        this.orderRecycles = orderRecycles;
        this.mContext = mContext;
    }

    @Override
    public OrderItemAdapter.OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.cart_item, parent, false);
        OrderItemAdapter.OrderItemViewHolder viewHolder = new OrderItemAdapter.OrderItemViewHolder(cartView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderItemAdapter.OrderItemViewHolder holder, int position) {
        OrderRecycle orderRecycle = orderRecycles.get(position);
        holder.productName.setText(orderRecycle.representProduct.getProductDTO().getName());
        holder.numberOfItems.setText(String.valueOf(orderRecycle.numberOfItems));
        holder.orderTotalMoney.setText(String.valueOf(orderRecycle.totalMoney / 1000) + ".000 đ");
        holder.orderStatus.setText(orderRecycle.status);
        int drawableId = mContext.getResources().getIdentifier(orderRecycle.representProduct.getProductDTO().getImage(), "drawable", mContext.getPackageName());
        holder.productImage.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return orderRecycles.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public ImageView productImage;
        public TextView numberOfItems;
        public TextView orderTotalMoney;
        public TextView orderStatus;
        public Button detailBtn;
        public TextView writeReviewBtn;


        public OrderItemViewHolder(View itemView, OnOrderItemClickListener listener) {
            super(itemView);

            productName = itemView.findViewById(R.id.orderProductName);
            numberOfItems = itemView.findViewById(R.id.orderQuantity);
            productImage = itemView.findViewById(R.id.imageOrderProduct);
            orderTotalMoney = itemView.findViewById(R.id.orderTotalMoney);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            detailBtn = itemView.findViewById(R.id.detailBtn);
            writeReviewBtn = itemView.findViewById(R.id.writeReviewTxt);

            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDetailBtnClick(position);
                    }
                }
            });
            writeReviewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onWriteReviewBtnClick(position);
                    }
                }
            });
        }
    }
}

