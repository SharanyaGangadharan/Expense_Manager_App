package com.example.acer.mysqltest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by acer on 5/15/2016.
 */
public class PieGraph {

    public Intent getIntent(Context context) {
            int[] values = {130,15};

            CategorySeries series = new CategorySeries("Pie Graph");
            int k = 0;
            for (int value : values) {
                series.add("Section " + ++k, value);
            }

            int[] colors = new int[]{Color.BLUE, Color.GREEN};
            DefaultRenderer renderer = new DefaultRenderer();
            for (int color : colors) {
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                renderer.addSeriesRenderer(r);
            }
            renderer.setChartTitle("Pie Chart");
            renderer.setChartTitleTextSize(7);
            renderer.setZoomButtonsVisible(true);


            Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
            return intent;
        }
}
