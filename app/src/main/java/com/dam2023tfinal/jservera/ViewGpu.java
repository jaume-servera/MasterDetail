package com.dam2023tfinal.jservera;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewGpu extends AppCompatActivity {

    private static final String TAG = "ViewGpu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lisitng);

        TextView modelName = findViewById(R.id.modelName);
        TextView manufacuturer = findViewById(R.id.manufacturer);
        TextView launchDate = findViewById(R.id.launchDate);
        TextView vramSize = findViewById(R.id.memorySize);
        TextView vramType = findViewById(R.id.memoryType);
        TextView clockSpeed = findViewById(R.id.coreClockSpeed);
        TextView boostSpeed = findViewById(R.id.boostClockSpeed);
        TextView processingUnits = findViewById(R.id.processingUnits);
        TextView TDP = findViewById(R.id.TDP);
        TextView hdmiNumber = findViewById(R.id.hdmiNumber);
        TextView displayPortNumber = findViewById(R.id.displayPortNumber);
        TextView vgaNumber = findViewById(R.id.vgaNumber);
        TextView dviNumber = findViewById(R.id.dviNumber);
        TextView supportRayTracing = findViewById(R.id.supportRayTracing);
        TextView numberOfTransistors = findViewById(R.id.numberOfTransistors);
        TextView dimensions = findViewById(R.id.dimensions);
        TextView price = findViewById(R.id.price);

        //Get the gpu object from the intent
        gpu gpu = (gpu) getIntent().getSerializableExtra("gpu");

        assert gpu != null;
        Log.d(TAG, gpu.toString());

        //Set the text views
        setTextViewWithPlaceholder(modelName, gpu.getModel(), "Model");
        setTextViewWithPlaceholder(manufacuturer, gpu.getManufacturer(), "Manufacturer");
        setTextViewWithPlaceholder(launchDate, String.valueOf(gpu.getLaunchDate()), "Not Specified");
        setTextViewWithPlaceholder(vramSize, String.valueOf(gpu.getMemorySize()), "N/A");
        setTextViewWithPlaceholder(vramType, gpu.getMemoryType(), "Not Specified");
        setTextViewWithPlaceholder(clockSpeed, String.valueOf(gpu.getCoreClockSpeed()), "N/A");
        setTextViewWithPlaceholder(boostSpeed, String.valueOf(gpu.getBoostClockSpeed()), "N/A");
        setTextViewWithPlaceholder(processingUnits, String.valueOf(gpu.getProcessingUnits()), "N/A");
        setTextViewWithPlaceholder(TDP, String.valueOf(gpu.getTDP()), "N/A");
        setTextViewWithPlaceholder(hdmiNumber, String.valueOf(gpu.getHdmiNumber()), "N/A");
        setTextViewWithPlaceholder(displayPortNumber, String.valueOf(gpu.getDisplayPortNumber()), "N/A");
        setTextViewWithPlaceholder(vgaNumber, String.valueOf(gpu.getVgaNumber()), "N/A");
        setTextViewWithPlaceholder(dviNumber, String.valueOf(gpu.getDviNumber()), "N/A");
        setTextViewWithPlaceholder(supportRayTracing, String.valueOf(gpu.isSupportRayTracing()), "N/A");
        setTextViewWithPlaceholder(numberOfTransistors, String.valueOf(gpu.getNumberOfTransistors()), "N/A");
        setTextViewWithPlaceholder(dimensions, gpu.getDimensions(), "Not Specified");
        setTextViewWithPlaceholder(price, String.valueOf(gpu.getPrice()), "N/A");

    }

    private void setTextViewWithPlaceholder(TextView textView, String value, String placeholder) {
        if (value == null || value.isEmpty()) {
            textView.setText(placeholder);
        } else {
            textView.setText(value);
        }
    }
}
