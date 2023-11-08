package com.dam2023tfinal.jservera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewGpuActivity extends AppCompatActivity {

    private TextView modelName;
    private Spinner manufacturerSelector;
    private EditText editTextDate;
    private Spinner vramSelector;
    private Spinner vramTypeSelector;
    private Slider clockSpeedSlider;
    private TextView clockSpeedValue;
    private Slider boostSpeedSlider;
    private TextView boostSpeedValue;
    private EditText editProcessingUnits;
    private EditText editTDP;
    private Spinner hdmiSelector;
    private Spinner displayPortSelector;
    private Spinner vgaSelector;
    private Spinner dviSelector;
    private Switch switch1;
    private EditText editTransistorNumber;
    private EditText editDimensions;
    private EditText editTextNumber4;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        // Initialize UI elements
        modelName = findViewById(R.id.modelName);
        manufacturerSelector = findViewById(R.id.manufacturerSelector);
        //editTextDate = findViewById(R.id.editTextDate);
        vramSelector = findViewById(R.id.vramSelector);
        vramTypeSelector = findViewById(R.id.vramTypeSelector);
        clockSpeedSlider = findViewById(R.id.clockSpeedSlider);
        clockSpeedValue = findViewById(R.id.clockSpeedValue);
        boostSpeedSlider = findViewById(R.id.boostSpeedSlider);
        boostSpeedValue = findViewById(R.id.boostSpeedValue);
        editProcessingUnits = findViewById(R.id.editProcessingUnits);
        editTDP = findViewById(R.id.editTDP);
        hdmiSelector = findViewById(R.id.hdmiSelector);
        displayPortSelector = findViewById(R.id.displayPortSelector);
        vgaSelector = findViewById(R.id.vgaSelector);
        dviSelector = findViewById(R.id.dviSelector);
        switch1 = findViewById(R.id.switch1);
        editTransistorNumber = findViewById(R.id.editTransistorNumber);
        editDimensions = findViewById(R.id.editDimensions);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        saveButton = findViewById(R.id.saveButton);




        // Set up a click listener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGpuData();
            }
        });
    }

    private void saveGpuData() {
        gpu newGpu = (gpu) getIntent().getExtras().getSerializable("gpu");

        newGpu.setModel(modelName.getText().toString());
        newGpu.setManufacturer(manufacturerSelector.getSelectedItem().toString());
        newGpu.setMemorySize(vramSelector.getSelectedItem().toString().equals("") ? 0 : Integer.parseInt(vramSelector.getSelectedItem().toString()));;
        newGpu.setMemoryType(vramTypeSelector.getSelectedItem().toString());
        newGpu.setCoreClockSpeed(clockSpeedSlider.getValue());
        newGpu.setBoostClockSpeed(boostSpeedSlider.getValue());
        newGpu.setProcessingUnits(editProcessingUnits.getText().toString().equals("") ? 0 : Integer.parseInt(editProcessingUnits.getText().toString()));
        newGpu.setTDP(editTDP.getText().toString().equals("") ? 0 : Integer.parseInt(editTDP.getText().toString()));
        newGpu.setHdmiNumber(hdmiSelector.getSelectedItem().toString().equals("") ? 0 : Integer.parseInt(hdmiSelector.getSelectedItem().toString()));
        newGpu.setDisplayPortNumber(Integer.parseInt(displayPortSelector.getSelectedItem().toString()));
        newGpu.setVgaNumber(vgaSelector.getSelectedItem().toString().equals("") ? 0 : Integer.parseInt(vgaSelector.getSelectedItem().toString()));
        newGpu.setDviNumber(dviSelector.getSelectedItem().toString().equals("") ? 0 : Integer.parseInt(dviSelector.getSelectedItem().toString()));

// After modifying the gpuList
        Intent resultIntent = new Intent();
        resultIntent.putExtra("gpu", newGpu);
        setResult(RESULT_OK, resultIntent);

        finish();

    }


}
