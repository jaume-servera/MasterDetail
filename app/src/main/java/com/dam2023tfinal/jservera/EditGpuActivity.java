package com.dam2023tfinal.jservera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

public class EditGpuActivity extends AppCompatActivity {

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

    private gpu selectedGpu; // Store the selected GPU for editing

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
        editTextNumber4 = findViewById(R.id.avgPrice);
        saveButton = findViewById(R.id.saveButton);

        // Get the selected GPU from the Intent
        selectedGpu = (gpu) getIntent().getSerializableExtra("gpu");

        if (selectedGpu != null) {
            // Set the data of the selected GPU to the UI elements for editing
            modelName.setText(selectedGpu.getModel());
            manufacturerSelector.setSelection(selectedGpu.getManufacturerIndex());
            //editTextDate.setText(selectedGpu.getLaunchDate());
            vramSelector.setSelection(selectedGpu.getMemorySizeIndex());
            vramTypeSelector.setSelection(selectedGpu.getMemoryTypeIndex());
            clockSpeedSlider.setValue((float) selectedGpu.getCoreClockSpeed() == 0 ? (float) 1000.00 : (float) selectedGpu.getCoreClockSpeed());
            boostSpeedSlider.setValue((float) selectedGpu.getBoostClockSpeed() == 0 ? (float) 1000.00 : (float) selectedGpu.getBoostClockSpeed());
            clockSpeedValue.setText(String.valueOf(selectedGpu.getCoreClockSpeed()));
            boostSpeedValue.setText(String.valueOf(selectedGpu.getBoostClockSpeed()));
            editProcessingUnits.setText(String.valueOf(selectedGpu.getProcessingUnits()));
            editTDP.setText(String.valueOf(selectedGpu.getTDP()));
            hdmiSelector.setSelection(selectedGpu.getHdmiNumber());
            displayPortSelector.setSelection(selectedGpu.getDisplayPortNumber());
            vgaSelector.setSelection(selectedGpu.getVgaNumber());
            dviSelector.setSelection(selectedGpu.getDviNumber());
            switch1.setChecked(selectedGpu.isSupportRayTracing());
            editTransistorNumber.setText(String.valueOf(selectedGpu.getNumberOfTransistors()));
            editDimensions.setText(selectedGpu.getDimensions());

        }

        // Set up a click listener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditedGpuData();
            }
        });
    }

    private void saveEditedGpuData() {
        if (selectedGpu != null) {
            // Update the selected GPU with the edited information
            selectedGpu.setModel(modelName.getText().toString());
            selectedGpu.setManufacturer(manufacturerSelector.getSelectedItem().toString());
            //selectedGpu.setLaunchDate(editTextDate.getText().toString());
            selectedGpu.setMemorySize(Integer.parseInt(vramSelector.getSelectedItem().toString()));
            selectedGpu.setMemoryType(vramTypeSelector.getSelectedItem().toString());
            selectedGpu.setCoreClockSpeed((int) clockSpeedSlider.getValue());
            selectedGpu.setBoostClockSpeed((int) boostSpeedSlider.getValue());
            selectedGpu.setProcessingUnits(Integer.parseInt(editProcessingUnits.getText().toString()));
            selectedGpu.setTDP(Integer.parseInt(editTDP.getText().toString()));
            selectedGpu.setHdmiNumber(Integer.parseInt(hdmiSelector.getSelectedItem().toString()));
            selectedGpu.setDisplayPortNumber(Integer.parseInt(displayPortSelector.getSelectedItem().toString()));
            selectedGpu.setVgaNumber(Integer.parseInt(vgaSelector.getSelectedItem().toString()));
            selectedGpu.setDviNumber(Integer.parseInt(dviSelector.getSelectedItem().toString()));
            selectedGpu.setSupportRayTracing(switch1.isChecked());
            selectedGpu.setNumberOfTransistors(Integer.parseInt(editTransistorNumber.getText().toString()));
            selectedGpu.setDimensions(editDimensions.getText().toString());
            selectedGpu.setPrice(Integer.parseInt(editTextNumber4.getText().toString().equals("") ? "0" : editTextNumber4.getText().toString()));
            String selectedVram = vramSelector.getSelectedItem().toString();
            if (!selectedVram.isEmpty()) {
                selectedGpu.setMemorySize(Integer.parseInt(selectedVram));
            } else {
                // Handle the case when the selected value is empty
                selectedGpu.setMemorySize(0);
            }

            // Prepare the edited GPU to send back to the calling activity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("editedGpu", selectedGpu);
            setResult(20, resultIntent);

            finish(); // Finish the activity and return to the calling activity
        }
    }
}
