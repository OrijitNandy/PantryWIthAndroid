package com.example.shopaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase sq;

    FloatingActionButton fbShoppingListCreate;

    List<Shoplist> shopList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.recyclerViewShoppingList);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

        fbShoppingListCreate = (FloatingActionButton) findViewById(R.id.ShoppingListCreate);

        ShoplistViewData();

        fbShoppingListCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShoplistCreateActivity.class));
            }
        });
    }

    private void ShoplistViewData() {
        shopList = new ArrayList<>();

        sq = dbHelper.getReadableDatabase();

        Cursor res = sq.rawQuery("SELECT * FROM shopping_list",null);

        if (res.getCount() == 0 ){
            Toast.makeText(ShoppingListActivity.this, "Database is empty!", Toast.LENGTH_LONG).show();
            return;
        }
        while(res.moveToNext()){
            shopList.add(
                    new Shoplist(res.getString(0), res.getString(1), res.getString(2) ,res.getString(3)));
        }

        //creating recyclerview adapter
        ShopListAdapter adapter = new ShopListAdapter( ShoppingListActivity.this, this, shopList );

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }

}