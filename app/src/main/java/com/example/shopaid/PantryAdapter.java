package com.example.shopaid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Pantry> pantryList;
    Activity activity;

    //getting the context and product list with constructor
    public PantryAdapter(Activity activity,Context mCtx, List<Pantry> pantryList)
    {
        this.activity = activity;
        this.mCtx = mCtx;
        this.pantryList = pantryList;
    }

    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.viewitems_layout, null);
//        return new PantryViewHolder(view);
        View view = LayoutInflater.from(mCtx).inflate(R.layout.viewitems_layout, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PantryViewHolder holder, int position) {
        //getting the Pantry of the specified position
        Pantry pantry = pantryList.get(position);

        String id = pantry.getId();
        String name= pantry.getName();
        String  price= pantry.getPrice();
        String quantity = pantry.getQuantity();

        holder.textViewName.setText(id);
        holder.textViewName.setText(name);
        holder.textViewPrice.setText(price);
        holder.textViewQuantity.setText(quantity);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( mCtx , PantryUpdateActivity.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("name", String.valueOf(name));
                intent.putExtra("price", String.valueOf(price));
                intent.putExtra("quantity", String.valueOf(quantity));
                activity.startActivityForResult(intent, 1);
            }
        });



        //binding the data with the viewholder views
//        holder.textViewName.setText(pantry.getName());
//        holder.textViewQuantity.setText(Pantry.getQuantity());
//////        holder.textViewRating.setText(String.valueOf(Pantry.getRating()));
//        holder.textViewPrice.setText(String.valueOf(pantry.getPrice()));

//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(Pantry.getImage()));


//        holder.textViewName.setText(pantryList.get(position).getName());
//        holder.textViewPrice.setText(pantryList.get(position).getPrice());
//        holder.textViewQuantity.setText(pantryList.get(position).getQuantity());
    }


    @Override
    public int getItemCount() {

        return pantryList.size();
    }


    class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewQuantity, textViewRating, textViewPrice;
        LinearLayout mainLayout;
//        ImageView imageView


        public PantryViewHolder(View itemView) {
            super(itemView);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
//            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
//            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
