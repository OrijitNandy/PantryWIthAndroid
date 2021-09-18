package com.example.shopaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoplistCreateActivity extends AppCompatActivity {

    EditText editTextId,editTextLoc,editTextDate,editTextName;
    DatabaseHelper dbHelper;
    SQLiteDatabase sq;
    Button buttonAdd, itemAdd;
    RecyclerView recyclerView;

    List<Shoplist> shopList;
    List<Pantry> pantryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist_create);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DatabaseHelper(this);

        buttonAdd = (Button) findViewById(R.id.buttonAddCart);
        itemAdd = (Button) findViewById(R.id.button3);

        editTextName = (EditText) findViewById(R.id.editTextName3);
        editTextLoc = (EditText) findViewById(R.id.editTextPrice3);
        editTextDate = (EditText) findViewById(R.id.editTextQuantity3);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

//        ViewData();

        itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sq = dbHelper.getWritableDatabase();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("shop_name",editTextName.getText().toString() );
                        contentValues.put("shop_address", editTextLoc.getText().toString());
                        contentValues.put("date", editTextDate.getText().toString());

                        long isInserted = sq.insert("shopping_list", null , contentValues);

                        if (isInserted < 0){
                            Toast.makeText(ShoplistCreateActivity.this, "Process Failed!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ShoplistCreateActivity.this, "Process Successful!", Toast.LENGTH_LONG).show();

                            editTextName.setText("");
                            editTextLoc.setText("");
                            editTextDate.setText("");

                            Intent i = new Intent(getApplicationContext(), ShoppingListActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }

                    }
                }

        );
    }

    public void ViewData() {
        pantryList = new ArrayList<>();
        sq = dbHelper.getReadableDatabase();

        Cursor res = sq.rawQuery("SELECT * FROM pantry_list",null);

        if (res.getCount() == 0 ){
            Toast.makeText(ShoplistCreateActivity.this, "Database is empty!", Toast.LENGTH_LONG).show();
            return;
        }
        while(res.moveToNext()){
            pantryList.add(
                    new Pantry(res.getString(0), res.getString(1), res.getString(2) ,res.getString(3)));
        }

        //creating recyclerview adapter
        PantryAdapter adapter = new PantryAdapter(ShoplistCreateActivity.this, this, pantryList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }


    private void ShoplistViewData() {
        shopList = new ArrayList<>();

        sq = dbHelper.getReadableDatabase();

        Cursor res = sq.rawQuery("SELECT * FROM shopping_list",null);

        if (res.getCount() == 0 ){
            Toast.makeText(ShoplistCreateActivity.this, "Database is empty!", Toast.LENGTH_LONG).show();
            return;
        }
        while(res.moveToNext()){
            shopList.add(
                    new Shoplist(res.getString(0), res.getString(1), res.getString(2) ,res.getString(3)));
        }

        //creating recyclerview adapter
        ShopListAdapter adapter = new ShopListAdapter( ShoplistCreateActivity.this, this, shopList );

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),ShoplistCreateActivity.class));
//            }
//        });
    }
}