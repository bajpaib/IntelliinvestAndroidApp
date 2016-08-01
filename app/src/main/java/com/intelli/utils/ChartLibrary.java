package com.intelli.utils;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.intelli.intelliinvest.R;

import java.util.ArrayList;

/**
 * Created by Pooja on 03-04-2016.
 */
public class ChartLibrary {

    public static void loadLineChart(Context context, LineChart chartView, ArrayList<Float> chartData, ArrayList<String> xLabel, String desc)
    {
        int highlightIndex = 2;

        int[] colors = new int[chartData.size()];

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i<chartData.size(); i++)
        {
            entries.add(new Entry(chartData.get(i), i));
            if(i==highlightIndex) {
                colors[i] = R.color.darkred;
            }else {
                colors[i] = R.color.darkgreen;
            }
        }

        LineDataSet dataSet = new LineDataSet(entries, "# Macro Analysis");
        dataSet.setCircleColors(colors, context);
        dataSet.setCircleRadius(4f);
//        dataSet.setLineWidth(3f);
//        dataSet.setValueTextSize(20f);
//        dataSet.enableDashedLine(6f, 18f, 0);

//        dataSet.setDrawCubic(true); //for curvy lines
//        dataSet.setDrawFilled(true); //fill color below line
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); //set color of lines

        LineData data = new LineData(xLabel, dataSet);

        chartView.setData(data); // set the data and list of labels into chart
        chartView.setDescription(desc);
        chartView.setDrawBorders(true);
        chartView.setBorderColor(R.color.theme_dark);
        chartView.setBorderWidth(1.5f);
    }

    public static void getScatterChart(ScatterChart chartView, ArrayList<Float> chartData, ArrayList<String> xLabel)
    {
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i<chartData.size(); i++)
        {
            entries.add(new Entry(chartData.get(i), i));
        }

        ScatterDataSet dataSet = new ScatterDataSet(entries, "# of Calls");
//        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE); // set the shape of drawn scatter point.
//        dataSet.setScatterShapeSize(10); // set the size of scatter shape
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // set the color
        chartView.animateY(1000);

        ScatterData data = new ScatterData(xLabel, dataSet);

        chartView.setData(data); // set the data and list of labels into chart
        chartView.setDescription("Description");
    }

}
