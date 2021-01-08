package com.example.xcustom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView cartBtn = findViewById(R.id.cartbtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Checkout.class);
                startActivity(intent);
            }
        });


        ArrayList<Item> itemList = new ArrayList<>(); //Array of lists

        Item i1 = new Item("Hoodies",R.drawable.hoodies);
        Item i2 = new Item("Canvas",R.drawable.canvas);
        Item i3 = new Item("Mug",R.drawable.mug);

        itemList.add(i1);
        itemList.add(i2);
        itemList.add(i3);

        RecyclerView rv = findViewById(R.id.rv);
        //Alligning recyclerview with linear layout  To setup cell size of recycler view
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        //





        item_adapter item_adapter = new item_adapter(itemList,MainActivity.this);
        rv.setAdapter(item_adapter);





    }
}