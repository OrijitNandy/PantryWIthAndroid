package com.example.shopaid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewholder> {

    List<Shoplist> shoplistList;
    Context context;
    Activity activity;

    public ShopListAdapter (Activity activity,Context context, List<Shoplist> shoplistList) {
        this.context = context;
        this.shoplistList = shoplistList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ShopListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item_view_layout, parent, false);
        return new ShopListViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListViewholder holder, int position) {

        String shopId = shoplistList.get(position).getShopId();
        String shopName = shoplistList.get(position).getShopName();
        String shopLoc = shoplistList.get(position).getshopLoc();
        String date = shoplistList.get(position).getDate();

        holder.shopId.setText(shopId);
        holder.shopName.setText(shopName);
        holder.shopLoc.setText(shopLoc);
        holder.date.setText(date);

    }

    @Override
    public int getItemCount() {
        return shoplistList.size();
    }

    public class ShopListViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView shopId,shopName,shopLoc,date;
        public ShopListViewholder(@NonNull View itemView) {
            super(itemView);

            shopId = itemView.findViewById(R.id.textViewRating);
            shopName = itemView.findViewById(R.id.textViewName);
            shopLoc = itemView.findViewById(R.id.textViewPrice);
            date = itemView.findViewById(R.id.textViewQuantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent( context , PantryUpdateActivity.class);
            intent.putExtra("id", String.valueOf(shopId));
            intent.putExtra("name", String.valueOf(shopName));
            intent.putExtra("location", String.valueOf(shopLoc));
            intent.putExtra("date", String.valueOf(date));
            activity.startActivityForResult(intent, 1);
        }
    }
}
