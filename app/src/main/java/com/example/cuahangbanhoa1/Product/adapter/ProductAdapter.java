package com.example.cuahangbanhoa1.Product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbanhoa1.Product.Model.Product;
import com.example.cuahangbanhoa1.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private Context context;


    public ProductAdapter( Context context,ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View listItem =layoutInflater.inflate(R.layout.item_product,parent,false);
        ViewHolder viewHolder= new ProductAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final  Product p = products.get(position);
        String sProductName = p.getName();
        holder.txtProductName.setText(sProductName);
        holder.txtPrice.setText(""+p.getPrice());
        holder.ivProductImage.setImageURI(p.getImage());
        holder.txtDescription.setText(p.getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName;
        public TextView txtPrice;
        public RelativeLayout relativeLayout;
        public TextView txtDescription;

        public ImageView ivAdd;
        public ImageView ivProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            this.txtDescription=(TextView) itemView.findViewById(R.id.tvDescription);
            this.txtPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            this.ivAdd = (ImageView) itemView.findViewById(R.id.ivAdd);
            this.ivProductImage = (ImageView) itemView.findViewById(R.id.ivProductImage);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout);

        }
    }
}