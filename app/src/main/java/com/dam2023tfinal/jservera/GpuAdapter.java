package com.dam2023tfinal.jservera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the position of the item in the adapter
                int position = getAdapterPosition();
                // Check for a valid position and call the method to start EditGpuActivity
                if(position != RecyclerView.NO_POSITION) {
                    // Ensure the context is indeed MainActivity before casting
                    Context context = v.getContext();
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).startEditGpuActivity(position);
                    }
                }
            }
        });

        // Load and set the GPU image using Glide
        //Glide.with(holder.itemView.getContext())
        //        .load(currentGpu.getImageUrl())  // Replace with the actual URL or resource for the GPU image
        //        .into(holder.gpuItemImage);
    }

    private int getAdapterPosition() {
        return gpuList.indexOf(this);
    }

    @Override
    public int getItemCount() {
        return gpuList.size();
    }

    public class GpuViewHolder extends RecyclerView.ViewHolder {
        
        public TextView gpuName, gpuPrice;
        public Button editButton;

        public GpuViewHolder(View view) {
            super(view);
            
            gpuName = view.findViewById(R.id.gpuName);
            gpuPrice = view.findViewById(R.id.gpuPrice);
            editButton = view.findViewById(R.id.editButton);
        }
    }
}


