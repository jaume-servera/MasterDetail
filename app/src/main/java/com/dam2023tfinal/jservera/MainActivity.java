package com.dam2023tfinal.jservera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GpuAdapter adapter;
    private List<gpu> gpuList;

    private static final String TAG = "MainActivity";

    @SuppressLint("NotifyDataSetChanged")
    private final ActivityResultLauncher<Intent> startNewGpuActivityForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == 10) {
                    gpu newGpu = (gpu) result.getData().getSerializableExtra("gpu");
                    gpuList.add(newGpu);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "onActivityResult: " + newGpu.getModel());
                }
                if (result.getResultCode() == 20) {
                    gpu editedGpu = (gpu) result.getData().getSerializableExtra("gpu");
                    int editedIndex = -1;
                    for (int i = 0; i < gpuList.size(); i++) {
                        if (gpuList.get(i).getId() == editedGpu.getId()) {
                            editedIndex = i;
                            break;
                        }
                    }

                    if (editedIndex != -1) {
                        // Replace the old object with the edited one
                        gpuList.set(editedIndex, editedGpu);
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle when the edited GPU is not found
                        Log.e(TAG, "Edited GPU not found in the list");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar customToolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(customToolbar);

        recyclerView = findViewById(R.id.recyclerView);
        gpuList = new ArrayList<>(); // You need to fill this list with actual GPU data
        adapter = new GpuAdapter(gpuList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, ViewGpu.class);
            gpu clickedGpu = gpuList.get(position);
            intent.putExtra("gpu", clickedGpu);
            startActivity(intent);
        });


        gpu demoGpu = createDemoGpu();

        Log.d("MainActivity", "Before adding demoGpu to gpuList");
        Log.d("MainActivity", "gpuList size: " + gpuList.size());
        gpuList.add(demoGpu);

        Log.d("MainActivity", "After adding demoGpu to gpuList");
        Log.d("MainActivity", "gpuList size: " + gpuList.size());
        adapter.notifyDataSetChanged();
    }

    private gpu createDemoGpu() {
        gpu testGpu = new gpu();
        testGpu.setModel("rtx 3060");
        testGpu.setManufacturer("nvidia");
        testGpu.setPrice(329);
        testGpu.setMemorySize(12);
        testGpu.setMemoryType("gddr6");
        testGpu.setCoreClockSpeed(1320);
        testGpu.setBoostClockSpeed(1777);
        testGpu.setProcessingUnits(3584);
        testGpu.setTDP(170);
        testGpu.setHdmiNumber(1);
        testGpu.setDisplayPortNumber(3);
        testGpu.setVgaNumber(0);

        return testGpu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // Handle the "Add" button click
                // Start the new activity expecting a result
                Intent intent = new Intent(this, NewGpuActivity.class);
                gpu newGpu = new gpu();
                intent.putExtra("gpu", newGpu);
                startNewGpuActivityForResult.launch(intent);
                return true;
            case R.id.action_edit:
                //for each item in the recycler view, if editButton visibility is true, then set it to false and viceversa
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View view = recyclerView.getChildAt(i);
                    Button editButton = view.findViewById(R.id.editButton);
                    if (editButton.getVisibility() == View.VISIBLE) {
                        editButton.setVisibility(View.GONE);
                    } else {
                        editButton.setVisibility(View.VISIBLE);
                    }
                }

                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View view = recyclerView.getChildAt(i);
                    Button deleteButton = view.findViewById(R.id.deleteButton);
                    if (deleteButton.getVisibility() == View.VISIBLE) {
                        deleteButton.setVisibility(View.GONE);
                    } else {
                        deleteButton.setVisibility(View.VISIBLE);
                    }
                }

                return true;
            case R.id.listLenght:
                Toast.makeText(this, "List lenght: " + gpuList.size(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
