package com.example.xcustom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter {

    ArrayList<ItemHoodie> itemsArray;
    Context context;

    public Cart_Adapter(ArrayList<ItemHoodie> itemsArray, Context context) {
        this.itemsArray = itemsArray;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_cell_design,parent,false);

        // we have to write viewholder class first here in buttom of page
        Cart_Adapter.ViewHolder vh = new Cart_Adapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     ((Cart_Adapter.ViewHolder) holder).size.setText(itemsArray.get(position).getSize());
        ((ViewHolder) holder).price.setText(Double.toString(itemsArray.get(position).getPrice())+" KWD");
        ((ViewHolder) holder).text.setText(itemsArray.get(position).getInputText());
     //((Cart_Adapter.ViewHolder) holder).img.setImageResource(itemsArray.get(position).getImg());

     // // On click on the whole view (anywhere in the view)
     // ((item_adapter.ViewHolder) holder).v.setOnClickListener(new View.OnClickListener() {
     //     @Override
     //     public void onClick(View v) {
     //         //Jumping to movieDetails xml on clicking on each cell
     //         //jump from main activity to activity movie_details
     //         Intent i;
     //         if (position == 0)
     //             i = new Intent(context,Hoodies.class);
     //         else if (position == 1)
     //             i = new Intent(context,Canvas.class);
     //         else
     //             i = new Intent(context,Mug.class);

     //         context.startActivity(i);
     //     }
     // });



    }

    @Override
    public int getItemCount() {
        return itemsArray.size();
    }


    //In order to access each movie parameters (name,year,elc) we need View holder that takes an input view passed from v

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView text;
        public TextView size;
        public TextView price;
        //public ImageView delete;

        //Adding a view
        public View v;


        //constructer
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            //this is not an activity so we use a view v that we passed that is an activity

            text = itemView.findViewById(R.id.itemtext);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
        }
    }


}


