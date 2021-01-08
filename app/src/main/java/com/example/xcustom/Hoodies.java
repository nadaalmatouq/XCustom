package com.example.xcustom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.util.log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ornach.nobobutton.NoboButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import top.defaults.colorpicker.ColorPickerPopup;

public class Hoodies extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://xcustomdb-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    ImageView img;
    private Button btnSelect;
   // String size;

    private final int PICK_IMAGE_REQUEST = 22;
    private static final int IMAGE_REQUEST = 2;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    // view box to preview the selected color
    private View mColorPreview;

    // this is the default color of the preview box
    private int mDefaultColor;
    String url;
    String getInput;
   // TextView dropDesign = findViewById(R.id.textView2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoodies);



        TextView price = findViewById(R.id.price);
        price.setText("15.000 KWD");




        //Adding input Text
        ImageButton addTxt = findViewById(R.id.TxtDesignBtn);
        final TextView inputTxt = findViewById(R.id.inputTxt);


      //  NoboButton addToCart = new NoboButton(this);
        TextView addToCart = findViewById(R.id.addToCRT);
     //  addToCart.setText("Submit");
     //  addToCart.setTextColor(Color.RED);
     //  addToCart.setAllCaps(true);
     //  addToCart.setFontIcon("\uf138");
     //  addToCart.setIconPosition(NoboButton.POSITION_LEFT);
     //  addToCart.setBackgroundColor(Color.WHITE);
     //  addToCart.setFocusColor(Color.GRAY);
     //  addToCart.setBorderColor(Color.RED);
     //  addToCart.setBorderWidth(2);
     //  addToCart.setRadius(10);




        //ColorPicker


        // register two of the buttons with their
        // appropriate IDs

        TextView mPickColorButton = findViewById(R.id.textView5);
        // and also register the view which shows the
        // preview of the color chosen by the user
        mColorPreview = findViewById(R.id.preview_selected_color);

        // set the default color to 0 as it is black
        mDefaultColor = 0;

        // handling the Pick Color Button to open color
        // picker dialog
        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {


                        new ColorPickerPopup.Builder(Hoodies.this).initialColor(
                                Color.RED) // set initial color
                                // of the color
                                // picker dialog
                                .enableBrightness(
                                        true) // enable color brightness
                                // slider or not
                                .enableAlpha(
                                        true) // enable color alpha
                                // changer on slider or
                                // not
                                .okTitle(
                                        "Choose") // this is top right
                                // Choose button
                                .cancelTitle(
                                        "Cancel") // this is top left
                                // Cancel button which
                                // closes the
                                .showIndicator(
                                        true) // this is the small box
                                // which shows the chosen
                                // color by user at the
                                // bottom of the cancel
                                // button
                                .showValue(
                                        true) // this is the value which
                                // shows the selected
                                // color hex code
                                // the above all values can be made
                                // false to disable them on the
                                // color picker dialog.
                                .build()
                                .show(
                                        v,
                                        new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void
                                            onColorPicked(int color) {
                                                // set the color
                                                // which is returned
                                                // by the color
                                                // picker
                                                mDefaultColor = color;


                                                // now as soon as
                                                // the dialog closes
                                                // set the preview
                                                // box to returned
                                                // color
                                                mColorPreview.setBackgroundColor(mDefaultColor);

                                                inputTxt.setTextColor(mDefaultColor);

                                            }
                                        });
                    }
                });









        ImageButton btnSelect = (ImageButton) findViewById(R.id.button2);
         img = (ImageView) findViewById(R.id.img);


         //input dialog box
        ArrayAdapter<CharSequence> adapter;


        AlertDialog.Builder alertName = new AlertDialog.Builder(this);
        final EditText editTextName1 = new EditText(Hoodies.this);

        alertName.setTitle(" Insert a text");
// titles can be used regardless of a custom layout or not
        alertName.setView(editTextName1);
        LinearLayout layoutName = new LinearLayout(this);
        layoutName.setOrientation(LinearLayout.VERTICAL);
        layoutName.addView(editTextName1); // displays the user input bar
        alertName.setView(layoutName);

        addTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertName.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // variable to collect user input
                        // collectInput(); // analyze input (txt) in this method

                         getInput = editTextName1.getText().toString();

                        // ensure that user input bar is not empty
                        if (getInput ==null || getInput.trim().equals("")){
                            Toast.makeText(getBaseContext(), "Please add a text", Toast.LENGTH_LONG).show();
                        }
                        else {
                            inputTxt.setText(getInput);
                            inputTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            mColorPreview.setVisibility(View.VISIBLE);
                            mPickColorButton.setVisibility(View.VISIBLE);
                        }

                    }
                });

                alertName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel(); // closes dialog
                       }}); // display the dialog



                AlertDialog alertDialog = alertName.create();
                alertDialog.show();
            }
        });




        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);


        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("XS");
        categories.add("S");
        categories.add("M");
        categories.add("L");
        categories.add("XL");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



    addToCart.setBackgroundResource(R.drawable.buttonbc);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use it to move to checkout page with checkout btn

                ItemHoodie hoodie = new ItemHoodie(String.valueOf(spinner.getSelectedItem()),getInput,Integer.toString(mDefaultColor),imageUri.toString() );
               // ItemHoodie hoodie = new ItemHoodie(size.getText().toString(),color.getText().toString(),"https://cdn.pixabay.com/photo/2014/11/07/14/26/kuwait-towers-520612_960_720.jpg" );
              dbRef.child("Cart").push().setValue(hoodie);

                Intent intent= new Intent(Hoodies.this,Checkout.class);

                startActivity(intent);

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
               // openImage();

            }
        });



    }//end onCreate Bundle

    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("Image/");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private String getFileExtention (Uri uri){
        ContentResolver contentResolver = getContentResolver();
              MimeTypeMap mimeTypeMap =  MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));



    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data){
       super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
           imageUri = data.getData();
           img.setVisibility(View.VISIBLE);
          // dropDesign.setVisibility(View.INVISIBLE);
           img.setImageURI(imageUri);
           System.out.println("TTTEEEESSSSTTTT"+imageUri);

          // uploadImage();
           //Picasso.with(this).load(url).into(img);

       }

       }//end onActivityResult

  // @Override
  // protected void onActivityResult(int requestCode, int resultCode, Intent data){
  //     super.onActivityResult(requestCode, resultCode, data);
  //     if (resultCode == RESULT_OK && requestCode == IMAGE_REQUEST){
  //         imageUri = data.getData();
  //         img.setImageURI(imageUri);
  //         //uploadImage();
  //     }

  // }


    private void uploadImage()
    {

        // Code for showing progressDialog while uploading
       final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        if (imageUri != null) {

            // Defining the child of storageReference
           // StorageReference storageRef = storage.getReferenceFromUrl("gs:<bucket address>");
            //StorageReference ref = storageRef.child("child name");
            final StorageReference ref = FirebaseStorage.getInstance().getReference().child("images").child(System.currentTimeMillis()+"."+getFileExtention(imageUri));

            ref.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                     ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                         @Override
                         public void onSuccess(Uri uri) {
                             
                               url = uri.toString();
                             Log.d("Download url",url);
                             System.out.println("TTTEEEESSSSTTTT"+url);
                             progressDialog.dismiss();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d( "Download url", "Failed loaded uri: " + e.getMessage());
                        }
                    });
                }
            });




        }
    }



}//end activity