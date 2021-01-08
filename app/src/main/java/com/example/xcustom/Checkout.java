package com.example.xcustom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://xcustomdb-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        ImageView closeBtn = findViewById(R.id.closebtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Checkout.this,MainActivity.class);
                startActivity(intent);
            }
        });


        ArrayList<ItemHoodie> cartitems = new ArrayList<>();

        RecyclerView rv = findViewById(R.id.rv);
        //Alligning recyclerview with linear layout  To setup cell size of recycler view
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        //





        final Cart_Adapter cart_adapter = new Cart_Adapter(cartitems,Checkout.this);
        rv.setAdapter(cart_adapter);


        Query allItems = dbRef.child("Cart");

        allItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartitems.clear();

                for (DataSnapshot item : dataSnapshot.getChildren()){

                    ItemHoodie i = item.getValue(ItemHoodie.class);
                    cartitems.add(i);
                    System.out.println("TEEESSSSEETTT"+i.getInputText());


                }
                cart_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Checkout.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });





    }//end activity
}