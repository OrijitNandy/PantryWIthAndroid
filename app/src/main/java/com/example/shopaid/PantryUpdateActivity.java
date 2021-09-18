package com.example.shopaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PantryUpdateActivity extends AppCompatActivity {

    EditText editTextPrice,editTextQuantity,editTextName;
    DatabaseHelper dbHelper;
    SQLiteDatabase sq;
    Button buttonUpdate, buttonDelete;

    String id,name,price,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_update);
        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName2);
        editTextPrice = findViewById(R.id.editTextPrice2);
        editTextQuantity = findViewById(R.id.editTextQuantity2);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        getIntentData ();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sq = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put("name",editTextName.getText().toString() );
                contentValues.put("price", editTextPrice.getText().toString());
                contentValues.put("quantity", editTextQuantity.getText().toString());

                long result = sq.update("pantry_list" , contentValues , "id = ?" , new String[]{id});

                if (result < 0){
                    Toast.makeText(PantryUpdateActivity.this, "Update Failed!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(PantryUpdateActivity.this, "Update Successful!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sq = dbHelper.getWritableDatabase();

                long result = sq.delete("pantry_list" , "id = ?" , new String[]{id});

                if (result < 0){
                    Toast.makeText(PantryUpdateActivity.this, "Delete Failed!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(PantryUpdateActivity.this, "Delete Successful!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

            }
        });
    }

    void  getIntentData () {
        if (
                getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("quantity")
        ) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            quantity = getIntent().getStringExtra("quantity");

            editTextName.setText(name);
            editTextPrice.setText(price);
            editTextQuantity.setText(quantity);

        } else  {Toast.makeText(PantryUpdateActivity.this, " No Data ", Toast.LENGTH_LONG).show();}
    }

}