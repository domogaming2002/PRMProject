package com.example.projectprm.adapter;

import android.annotation.SuppressLint;
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

import com.example.projectprm.DAO.CategoryDAO;
import com.example.projectprm.Entity.Category;
import com.example.projectprm.R;
import com.example.projectprm.activity.Update_Category;

import java.util.List;

public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.CategoryHolder>{
    List<Category> categories;
    private Context context;
    private CategoryFragmentAdapter.CategoryListener CategoryListener;
    private CategoryDAO categoryDAO;

    public CategoryFragmentAdapter(Context context, List<Category> categories, CategoryDAO categoryDAO) {
        this.categories = categories;
        this.context = context;
        this.categoryDAO = categoryDAO;
    }

    public CategoryFragmentAdapter(CategoryFragmentAdapter.CategoryListener categoryListener) {
        CategoryListener = categoryListener;
    }

    public CategoryFragmentAdapter.CategoryListener getCategoryListener() {
        return CategoryListener;
    }
    public Category getCategoryAt (int position) {
        return categories.get(position);
    }

    public void setCategoryListener(CategoryFragmentAdapter.CategoryListener categoryListener) {
        CategoryListener = categoryListener;
    }
    public void setList(List<Category> list) {
        this.categories= list;
        notifyDataSetChanged(); // refesh
    }
    public CategoryFragmentAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryFragmentAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_fragment_item, parent, false);
        return new CategoryFragmentAdapter.CategoryHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFragmentAdapter.CategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        final Category p= categories.get(position);
        holder.tv_name.setText(p.name);
        holder.tv_image.setText(p.image);
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Update_Category.class);
                intent.putExtra("categoryId", p.categoryId);
                context.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDAO.deleteCategoryById(p.categoryId);
                categories.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, categories.size());
            }
        });

    }
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_name, tv_image;
        CardView layoutItem;
        Button updateBtn, deleteBtn;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.categoryNameTxt);
            tv_image = itemView.findViewById(R.id.categoryImageTxt);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            layoutItem = itemView.findViewById(R.id.layout_itemRV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CategoryListener != null) {
                CategoryListener.onItemClick(view, getAdapterPosition());
            }
        }

    }
    public interface CategoryListener {
        void onItemClick(View view, int position);

    }
}