package com.example.android_chart_02;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class PieChartOnChartValueSelectedListener implements OnChartValueSelectedListener {
    public static final String LOG_TAG = "PIE_CHART";
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        //trigger activities when entry is clicked
        Log.i(LOG_TAG, LOG_TAG+"->"+"onClick "+e.toString()+" h="+h.toString());
    }

    @Override
    public void onNothingSelected() {

    }
}