package com.dam2023tfinal.jservera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;

public class GpuAdapter extends RecyclerView.Adapter<GpuAdapter.GpuViewHolder> {
    private List<gpu> gpuList;

    public GpuAdapter(List<gpu> gpuList) {
        this.gpuList = gpuList;
    }

    @NonNull
    @Override
    public GpuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listing, parent, false);
        return new GpuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GpuViewHolder holder, int position) {
        gpu currentGpu = gpuList.get(position);

        // Set GPU name and price
        holder.gpuName.setText(currentGpu.getModel());
        holder.gpuPrice.setText(String.valueOf(currentGpu.getPrice()));

        // Load and set the GPU image using Glide
        //Glide.with(holder.itemView.getContext())
        //        .load(currentGpu.getImageUrl())  // Replace with the actual URL or resource for the GPU image
        //        .into(holder.gpuItemImage);
    }

    @Override
    public int getItemCount() {
        return gpuList.size();
    }

    public class GpuViewHolder extends RecyclerView.ViewHolder {
        
        public TextView gpuName, gpuPrice;

        public GpuViewHolder(View view) {
            super(view);
            
            gpuName = view.findViewById(R.id.gpuName);
            gpuPrice = view.findViewById(R.id.gpuPrice);
        }
    }
}


