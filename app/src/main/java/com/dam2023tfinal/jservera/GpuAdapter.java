package com.dam2023tfinal.jservera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;

public class GpuAdapter extends RecyclerView.Adapter<GpuAdapter.GpuViewHolder> {
    private List<gpu> gpuList;
    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClick(int position);
    }



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
    public void onBindViewHolder(@NonNull GpuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        gpu currentGpu = gpuList.get(position);

        // Set GPU name and price
        holder.gpuName.setText(currentGpu.getModel());
        holder.gpuPrice.setText(String.valueOf(currentGpu.getPrice() + "€"));
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditGpuActivity.class);
                intent.putExtra("gpu", currentGpu);
                v.getContext().startActivity(intent);
            }
        });
        
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpuList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gpuList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class GpuViewHolder extends RecyclerView.ViewHolder {

        public TextView gpuName, gpuPrice;
        public Button editButton;
        public Button deleteButton;

        public GpuViewHolder(View view) {
            super(view);

            gpuName = view.findViewById(R.id.gpuName);
            gpuPrice = view.findViewById(R.id.gpuPrice);
            editButton = view.findViewById(R.id.editButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }
}


