package com.APP.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;

import com.APP.shopping.Model.Products;
import com.APP.shopping.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class CategoryItems extends AppCompatActivity {
    ImageView BACK;
    Button dressBTN, coatBTN, blousesBTN, pantsBTN,skirtsBTN,sweatersBTN,HoodiesBTN;
    private DatabaseReference CategoriesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoy_items);

        user = FirebaseAuth.getInstance().getCurrentUser();
        CategoriesRef = FirebaseDatabase.getInstance().getReference().child("Products").child("category");
        BACK=findViewById(R.id.backPressed);

        dressBTN=findViewById(R.id.dress_button);
        coatBTN=findViewById(R.id.coats_button);
        blousesBTN=findViewById(R.id.Blouses_button);
        pantsBTN=findViewById(R.id.pants_button);
        skirtsBTN=findViewById(R.id.skirts_button);
        sweatersBTN=findViewById(R.id.sweaters_button);
        HoodiesBTN=findViewById(R.id.Hoodies_button);

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryItems.super.onBackPressed();
            }
        });

        dressBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Dresses");
                startActivity(i);

            }
        });
        coatBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Coats");
                startActivity(i);

            }
        });
        blousesBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Blouses");
                startActivity(i);

            }
        });
        skirtsBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Skirts");
                startActivity(i);

            }
        });
        pantsBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Pants");
                startActivity(i);

            }
        });
        HoodiesBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Hoodies");
                startActivity(i);

            }
        });
        sweatersBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryItems.this,ProductsOfCategory.class);
                i.putExtra("CategoryName","Sweaters");
                startActivity(i);
            }
        });
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        String CategoryName = getIntent().getExtras().get("category").toString();
//        switch (CategoryName){
//            case "Dresses":
//            {
//
//            }
//            case "Blouses":
//            {
//
//            }
//            case "Coats":
//            {
//
//            }
//            case "Hoodies":
//            {
//
//            }
//            case "Sweaters":
//            {
//
//            }
//            case "Skirts":
//            {
//
//            }

        //        super.onStart();
//        Query query = FirebaseDatabase.getInstance().getReference().child("Products").child("category");
//
//        FirebaseRecyclerOptions<Products> options =
//                new FirebaseRecyclerOptions.Builder<Products>().setQuery(CategoriesRef, Products.class).build();
//        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
//                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
//                    @Override
//                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position
//                            , @NonNull final Products model) {
//                        holder.txtProductName.setText(model.getPname());
//                        holder.txtProductPrice.setText("Price = " + model.getPrice() + "EGP.");
//                        Picasso.get().load(model.getImage()).into(holder.imageView);
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent = new Intent(CategoryItems.this, ProductDetailsActivity.class);
//                                intent.putExtra("pid", model.getPid());
//                                startActivity(intent);
//                            }
//                        });
//                    }
//
//                    @NonNull
//                    @Override
//                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
//                        ProductViewHolder holder = new ProductViewHolder(view);
//                        return holder;
//                    }
//                };
//                     recyclerView.setAdapter(adapter);
//                      adapter.startListening();



//            CategoriesRef.child("Products").child("category").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//             if (!task.isSuccessful()){
//             Log.e("firebase","Error getting data",task.getException());
//             }
//             else{
//                Log.d("firebase",String.valueOf(task.getResult().getValue()));
//             }
//            }
//        });

       }
