package com.example.closetifiy_finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private Context context;
    private List<Uri> itemsList;
    private List<Uri> selectedItems;
    private OnItemSelectedListener onItemSelectedListener;

    public ItemsAdapter(Context context, List<Uri> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        this.selectedItems = new ArrayList<>();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.onItemSelectedListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imagePath = itemsList.get(position);
        holder.imageView.setImageURI(imagePath);

        holder.itemView.setOnClickListener(v -> {
            if (context.getClass().getSimpleName().equals("HomeCloset")) {
                // Open item detail if in HomeCloset context
                Intent intent = new Intent(context, ItemDetailed.class);
                intent.putExtra("imageUri", imagePath.toString());
                intent.putExtra("position", position);
                context.startActivity(intent);
            } else if (context.getClass().getSimpleName().equals("SelectFits")) {
                // Handle selection for SelectFits context
                if (selectedItems.contains(imagePath)) {
                    selectedItems.remove(imagePath);
                    holder.imageView.setAlpha(1.0f);
                } else {
                    selectedItems.add(imagePath);
                    holder.imageView.setAlpha(0.5f);
                }

                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(selectedItems);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(List<Uri> selectedItems);
    }
}