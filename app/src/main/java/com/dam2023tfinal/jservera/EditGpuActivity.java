package com.dam2023tfinal.jservera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditGpuActivity extends AppCompatActivity {

    // UI references like in NewGpuActivity...
    private Button saveButton;
    private gpu currentGpu; // This will hold the GPU to be edited
    private int gpuIndex; // This will hold the index of the GPU in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        // Initialize UI elements...

        // Get the GPU data and index from the intent
        currentGpu = (gpu) getIntent().getSerializableExtra("currentGpu");
        gpuIndex = getIntent().getIntExtra("gpuIndex", -1);

        // Populate the UI with the current GPU data
        populateUIWithGpuData(currentGpu);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditedGpuData();
            }
        });
    }

    private void populateUIWithGpuData(gpu gpuData) {
        // Populate the UI fields with gpuData...
    }

    private void saveEditedGpuData() {
        // Get the updated data from UI components and update the currentGpu object...

        // Prepare data to send back to MainActivity
        Intent returnIntent = new Intent();
        returnIntent.putExtra("updatedGpu", currentGpu);
        returnIntent.putExtra("gpuIndex", gpuIndex);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
