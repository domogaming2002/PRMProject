package com.example.projectprm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.DAO.SupplierDAO;
import com.example.projectprm.Entity.Supplier;
import com.example.projectprm.R;
import com.example.projectprm.activity.Update_Supplier;

import java.util.List;

public class SupplierFragmentAdapter extends RecyclerView.Adapter<SupplierFragmentAdapter.SupplierHolder>{
    List<Supplier> suppliers;
    private Context context;
    private SupplierDAO supplierDAO;
    private SupplierFragmentAdapter.SupplierListener SupplierListener;

    public SupplierFragmentAdapter(Context context, List<Supplier> suppliers, SupplierDAO supplierDAO) {
        this.suppliers = suppliers;
        this.context = context;
        this.supplierDAO = supplierDAO;
    }

    public SupplierFragmentAdapter(SupplierFragmentAdapter.SupplierListener supplierListener) {
        SupplierListener = supplierListener;
    }

    public SupplierFragmentAdapter.SupplierListener getSupplierListener() {
        return SupplierListener;
    }
    public Supplier getSupplierAt (int position) {
        return suppliers.get(position);
    }

    public void setSupplierListener(SupplierFragmentAdapter.SupplierListener supplierListener) {
        SupplierListener = supplierListener;
    }
    public void setList(List<Supplier> list) {
        this.suppliers= list;
        notifyDataSetChanged(); // refesh
    }
    public SupplierFragmentAdapter(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public SupplierFragmentAdapter.SupplierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_fragment_item, parent, false);
        return new SupplierFragmentAdapter.SupplierHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull SupplierFragmentAdapter.SupplierHolder holder, int position) {
        final Supplier p= suppliers.get(position);
        holder.tv_name.setText("Name: "+p.name);
        holder.tv_email.setText("Email: " + p.email);
        holder.tv_address.setText("Address: " + p.address);
        holder.tv_phone.setText("Phone: " + p.phone);
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Update_Supplier.class);
                intent.putExtra("supplierId", p.supplyId);
                context.startActivity(intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supplierDAO.deleteSupplierById(p.supplyId);
                suppliers.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, suppliers.size());
            }
        });
    }
    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public class SupplierHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_name, tv_email, tv_phone, tv_address;
        CardView layoutItem;
        Button updateBtn, deleteBtn;

        public SupplierHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.supplierNameTxt);
            tv_email = itemView.findViewById(R.id.supplierEmailTxt);
            tv_phone = itemView.findViewById(R.id.supplierPhoneTxt);
            tv_address = itemView.findViewById(R.id.supplierAddressTxt);
            layoutItem = itemView.findViewById(R.id.layout_itemRV);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (SupplierListener != null) {
                SupplierListener.onItemClick(view, getAdapterPosition());
            }
        }

    }
    public interface SupplierListener {
        void onItemClick(View view, int position);

    }
}