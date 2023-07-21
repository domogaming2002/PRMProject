package com.example.projectprm.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.projectprm.DAO.ProductDAO;
import com.example.projectprm.DTO.AppDatabase;
import com.example.projectprm.Entity.Product;
import com.example.projectprm.R;
import com.example.projectprm.activity.Add_Product;
import com.example.projectprm.adapter.ProductFragmentAdapter;
import com.example.projectprm.adapter.ProductListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProductFragmentAdapter.ProductListener{

    RecyclerView recyclerView;
    ProductFragmentAdapter adapter;
    AppDatabase db;
    ProductDAO productDAO;
    List<Product> list;
    FloatingActionButton fab;
    SearchView searchView;
    public ProductFragment() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRoomDatabase();
        fab = view.findViewById(R.id.fab_Product);
        recyclerView = view.findViewById(R.id.productRecyclerView);
        searchView = view.findViewById(R.id.sv_product);

        list = new ArrayList<Product>();
        list = productDAO.getListProduct();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_Product.class);
                startActivity(intent);
            }
        });
        adapter = new ProductFragmentAdapter(list);
        adapter.setProductListener(ProductFragment.this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Product> list = productDAO.findProductByName(s);
                adapter.setList(list);
                return true;
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
    private void initRoomDatabase() {
        db = Room.databaseBuilder(requireContext(), AppDatabase.class, "PRM")
                .allowMainThreadQueries().build();
        productDAO = db.productDAO();

    }
}