package com.example.acer.mysqltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class PieC extends AppCompatActivity {
    public ArrayList<String> arrpn1 = new ArrayList<String>();
    public ArrayList<String> arrin1 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            ArrayList<String> arr = (ArrayList<String>)b.getStringArrayList("array_list");
            arrpn1 = arr ;
            ArrayList<String> arr1 = (ArrayList<String>)b.getStringArrayList("array_list1");
            arrin1 = arr1 ;
        }

        PieChart pieChart = (PieChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        for(int i=0 ; i<arrin1.size() ; i++)
        {
            entries.add(new Entry(Integer.parseInt(arrin1.get(i)),i));
        }
        /*entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));*/

        PieDataSet dataset = new PieDataSet(entries, "Categories");

        ArrayList<String> labels = new ArrayList<String>();
        for ( int j =0 ; j<arrpn1.size() ; j++ )
        {
            labels.add(arrpn1.get(j));
        }
        /*labels.add("Grocery");
        labels.add("Medical");
        labels.add("Toiletries");
        labels.add("Stationary");
        labels.add("May");
        labels.add("June");*/

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart.setDescription("Description");
        pieChart.setData(data);

        pieChart.animateY(5000);

        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

    }
}



