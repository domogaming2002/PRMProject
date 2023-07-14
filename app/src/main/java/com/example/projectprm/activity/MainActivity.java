package com.example.projectprm.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.projectprm.Entity.Category;
import com.example.projectprm.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//        Map<String, Object> category = new HashMap<>();
//        category.put("name", "Hoa quả");
//        category.put("parentId", "Hoa quả");
//        category.put("image", "Hoa quả");
//        category.put("isDelete", false);
//
//
//        List<Category> categories = new ArrayList<>();
//        categories.add(new Category("Hoa quả", 0, "", false));
//        categories.add(new Category("Đồ khô", 0, "", false));
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
////        // Add a new document with a generated ID
////        db.collection("users")
////                .add(user)
////                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
////                    @Override
////                    public void onSuccess(DocumentReference documentReference) {
////                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
////                    }
////                })
////                .addOnFailureListener(new OnFailureListener() {
////                    @Override
////                    public void onFailure(@NonNull Exception e) {
////                        Log.w(TAG, "Error adding document", e);
////                    }
////                });
//
//        db.collection("Categories")
//                .add(categories)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference categoriesRef = db.collection("categories");
        AddCategory(categoriesRef);
    }

    public void AddCategory(CollectionReference categoriesRef) {
        String categoryId = "122"; // the desired document ID
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Category 2");
        data.put("parentId", "456");
        data.put("image", "image.jpg");
        data.put("isDelete", false);
        categoriesRef.document(categoryId).set(data);
    }

    public void getCategory(CollectionReference categoriesRef) {
//        categoriesRef.whereEqualTo("name", "Category 2").get().addOnCanceledListener(command -> )
//        categoriesRef.document(categoryId).set(data);
    }
}