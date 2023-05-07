package com.APP.shopping;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOrdersHistoryChart extends AppCompatActivity {

    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    private final static String MyPREFERENCES = "MyPrefs"; //define two class fields
    private SharedPreferences mSharedPreferences;

   // String[] Products = new String[]{"Dresses", "Coats", "Hoodies", "Sweaters", "Blouses", "Skirts","Pants"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_history_chart);

        barChart = (BarChart) findViewById(R.id.BarChart);

        mSharedPreferences  = this.getSharedPreferences(MyPREFERENCES, Activity.MODE_PRIVATE);

        BarEntry();
        Add_to_chart();


//        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Month");
//        barDataSet1.setColor(Color.WHITE);
//        barDataSet1.setValueTextColor(Color.WHITE);
//        BarData data = new BarData(barDataSet1);
//        barChart.setData(data);
//        barChart.getDescription().setEnabled(false);

//        xAxis.setPosition(XAxis.XAxisPosition.TOP);
//        xAxis.setTextColor(Color.WHITE);
//        xAxis.setGranularity(1);
//        barChart.setDragEnabled(true);
//        barChart.setVisibleXRangeMaximum(3);
        //float barSpace = 0.1f;
       // float groupSpace = 0.5f;
//        data.setBarWidth(0.15f);
//        barChart.getXAxis().setAxisMinimum(0);
//        barChart.animate();
//        //barChart.groupBars(0, groupSpace, barSpace);
//        barChart.invalidate();
    }

    // array list for first set
    private void BarEntry(){
        int arr[]=new int[10];

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter=0;
                for (DataSnapshot snapshot_i : snapshot.getChildren()) {
                    String t=snapshot_i.child("totalAmount").getValue().toString();
                    int total=(int)Integer.parseInt(t) ;
                    arr[counter]=total;
                    String s=String.valueOf(counter);

                    editor.putInt(s, arr[counter]);
                    editor.apply();

                    counter++;
                }
            }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }

    private void Add_to_chart()  {



        barEntriesArrayList = new ArrayList<>();
        int counter=0;
        for(int i=0;i<10;i++){

            String s=String.valueOf(counter);
            int totalAmount = mSharedPreferences.getInt(s,0);
            barEntriesArrayList.add(new BarEntry(counter, totalAmount));
            counter++;

        }



        barDataSet = new BarDataSet(barEntriesArrayList, "History Chart");

        barData = new BarData(barDataSet);

        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.WHITE);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);


    }



}
