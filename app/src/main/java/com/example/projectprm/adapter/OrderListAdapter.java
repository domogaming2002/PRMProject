package com.example.projectprm.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DTO.OrderDTO;
import com.example.projectprm.R;

import java.util.Date;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    private List<OrderDTO> orderDTOList;
    private Activity activity;
    private OrderListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int orderId);
    }

    public void setOnItemClickListener(OrderListAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    public OrderListAdapter(List<OrderDTO> orderDTOList, Activity activity) {
        this.orderDTOList = orderDTOList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public OrderListAdapter.OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderListViewHolder orderListViewHolder = new OrderListViewHolder(orderListView);
        return orderListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        OrderDTO order= orderDTOList.get(position);
        int orderId = order.getOrderId();
        String orderCode = order.getOrderCode();
        Date orderDate = order.getOrderDate();
        int status = order.getStatus();
        holder.orderDate.setText(orderDate.toString());
        holder.orderStatus.setText(String.valueOf(order.getStatus()));
        holder.orderProductName.setText(orderCode);
    }

    @Override
    public int getItemCount() {
        return orderDTOList.size();
    }
    public class OrderListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView orderProductName, orderDate, orderStatus;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            orderProductName = itemView.findViewById(R.id.orderProductName);
            orderDate = itemView.findViewById(R.id.orderQuantity);
            orderStatus = itemView.findViewById(R.id.orderTotalMoney);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION && listener != null){
                OrderDTO order= orderDTOList.get(position);
                int orderId = order.getOrderId();
                listener.onItemClick(orderId);
            }
        }
    }
}