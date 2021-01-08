package com.example.xcustom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class item_adapter extends RecyclerView.Adapter {

    ArrayList<Item> itemsArray;
    Context context;

    public item_adapter(ArrayList<Item> itemsArray, Context context) {
        this.itemsArray = itemsArray;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_design,parent,false);

        // we have to write viewholder class first here in buttom of page
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).name.setText(itemsArray.get(position).getName());
        ((ViewHolder) holder).img.setImageResource(itemsArray.get(position).getImg());

        // On click on the whole view (anywhere in the view)
        ((ViewHolder) holder).v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Jumping to movieDetails xml on clicking on each cell
                //jump from main activity to activity movie_details
                Intent i;
               if (position == 0)
               i = new Intent(context,Hoodies.class);
               else if (position == 1)
                   i = new Intent(context,Canvas.class);
               else
                  i = new Intent(context,Mug.class);

                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return 3;
    }


    //In order to access each movie parameters (name,year,elc) we need View holder that takes an input view passed from v

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView name;
        //public ImageView delete;

        //Adding a view
        public View v;


        //constructer
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            //this is not an activity so we use a view v that we passed that is an activity
            img = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
        }
    }


}


