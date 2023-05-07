package com.APP.shopping;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AdminCategoryActivity extends AppCompatActivity {
    private TextView suit, dress, blouse;
    private TextView pants, denim, skirts;
    private TextView hoodies, sweaters, coats;
    private TextView glasses, shoeses, watches;
    private Button LogoutBtn, CheckOrdersBtn, OrdersHistoryChart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });

        OrdersHistoryChart = (Button) findViewById(R.id.admin_Chart_btn);
        OrdersHistoryChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,AdminOrdersHistoryChart.class);
                startActivity(intent);
            }
        });

        suit = (TextView) findViewById(R.id.Suits);
        blouse = (TextView) findViewById(R.id.blouses);
        dress = (TextView) findViewById(R.id.Dresses);
        pants = (TextView) findViewById(R.id.Pants);
        denim = (TextView) findViewById(R.id.DenimJeans);
        skirts = (TextView) findViewById(R.id.Skirts);
        hoodies = (TextView) findViewById(R.id.Hoodies);
        sweaters = (TextView) findViewById(R.id.Sweaters);
        coats = (TextView) findViewById(R.id.Coats);
        watches = (TextView) findViewById(R.id.Watches);
        glasses = (TextView) findViewById(R.id.Glasses);
        shoeses = (TextView) findViewById(R.id.Shoes);


        suit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "suits");
                startActivity(intent);
            }
        });
        blouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Blouses");
                startActivity(intent);
            }
        });


        dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Dresses");
                startActivity(intent);
            }
        });


        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Pants");
                startActivity(intent);
            }
        });


        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Glasses");
                startActivity(intent);
            }
        });


        denim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Denim Jeans");
                startActivity(intent);
            }
        });



        skirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Skirts");
                startActivity(intent);
            }
        });


        shoeses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Shoes");
                startActivity(intent);
            }
        });



        hoodies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Hoodies");
                startActivity(intent);
            }
        });


        coats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Coats");
                startActivity(intent);
            }
        });


        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Watches");
                startActivity(intent);
            }
        });


        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.APP.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Sweaters");
                startActivity(intent);
            }
        });
    }
}
