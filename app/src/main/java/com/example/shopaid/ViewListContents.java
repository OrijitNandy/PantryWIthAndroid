package com.example.shopaid;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    RecyclerView recyclerView;
    ImageView imageView;
    Uri image_uri;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewitems_layout);
        imageView = findViewById(R.id.imageview);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
        myDB = new DatabaseHelper(this);



        List<Pantry> pantryList = null;
        Cursor data = new DatabaseHelper(this).getAllData();

        if(data.getCount() ==0){
            Toast.makeText(ViewListContents.this,"The Database Is Empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                pantryList.add(
                        new Pantry(
                                data.getString(0),
                                data.getString(1),
                                data.getString(2),
                                data.getString(3)
                        )
                );
            }

        }
        PantryAdapter adapter = new PantryAdapter(ViewListContents.this,this , pantryList);
        recyclerView.setAdapter(adapter);
    }

}
