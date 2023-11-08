package com.dam2023tfinal.jservera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    private final ActivityResultLauncher<Intent> startNewGpuActivityForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    gpu newGpu = (gpu) result.getData().getSerializableExtra("gpu");
                    gpuList.add(newGpu);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "onActivityResult: " + newGpu.getModel());
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
        testGpu.setModel("Demo GPU Model");
        testGpu.setManufacturer("Demo Manufacturer");

        return testGpu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

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
                // Handle the "Edit" button click
                Toast.makeText(this, "Edit button clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.listLenght:
                Toast.makeText(this, "List lenght: " + gpuList.size(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Recover the gpu from the intent
    ActivityResultCallback<Intent> resultCallback = new ActivityResultCallback<Intent>() {
        @Override
        public void onActivityResult(Intent result) {
            gpu newGpu = (gpu) result.getExtras().getSerializable("gpu");
            gpuList.add(newGpu);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "onActivityResult: " + newGpu.getModel());

        }
    };

}


