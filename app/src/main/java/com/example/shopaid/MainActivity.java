package com.example.shopaid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase sq;

    FloatingActionButton fb, fb2;

    List<Pantry> pantryList;
    List<Shoplist> shopList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fb = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        fb2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        dbHelper = new DatabaseHelper(this);

        ViewData();



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PantryCreateActivity.class));
            }
        });
//        fb2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),ShoplistCreateActivity.class));
//            }
//        });
    }


    //        public void AddData()  {
//            buttonAdd.setOnClickListener(
//                    new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            boolean isInserted =  dbHelper.addData(editText.getText().toString(),
//                                    editTextPrice.getText().toString(),
//                                    editTextQuantity.getText().toString()
//                            );
//                                  if (!isInserted){
//                                      Toast.makeText(MainActivity.this, "Process Failed!", Toast.LENGTH_LONG).show();
//                                  }
//                                  else{
//                                      Toast.makeText(MainActivity.this, "Process Successful!", Toast.LENGTH_LONG).show();
//
//                                      editText.setText("");
//                                      editTextPrice.setText("");
//                                      editTextQuantity.setText("");
//                                  }
//
//                        }
//                    }
//
//            );
//        }
            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 1 ) {
                    recreate();
                }
            }


        public void ViewData() {
            pantryList = new ArrayList<>();
            sq = dbHelper.getReadableDatabase();

            Cursor res = sq.rawQuery("SELECT * FROM pantry_list",null);

            if (res.getCount() == 0 ){
                Toast.makeText(MainActivity.this, "Database is empty!", Toast.LENGTH_LONG).show();
                return;
            }
            while(res.moveToNext()){
                pantryList.add(
                        new Pantry(res.getString(0), res.getString(1), res.getString(2) ,res.getString(3)));
            }

            //creating recyclerview adapter
            PantryAdapter adapter = new PantryAdapter(MainActivity.this, this, pantryList);

            //setting adapter to recyclerview
            recyclerView.setAdapter(adapter);



//        buttonView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                       Cursor res = myDB.getListContents();
//                       if (res.getCount() == 0 ){
//                           Toast.makeText(MainActivity.this, "Database is empty!", Toast.LENGTH_LONG).show();
//                           return;
//                       }
//                       StringBuffer buffer = new StringBuffer();
//                       while(res.moveToNext()){
//                           buffer.append("ID : " + res.getString(0) + "\n");
//                           buffer.append("Name : " + res.getString(1) + "\n");
//                           buffer.append("Price : " + res.getString(2) + "\n");
//                           buffer.append("Quantity : " + res.getString(3) + "\n");
//                       }
//                       showmessage("Data", buffer.toString());
//                    }
//                }
//        );
    }



//    public  void showmessage(String title, String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setMessage(Message);
//        builder.show();
//    }
//    public void UpdateData() {
//        buttonUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isUpdate = myDB.updateData(editTextId.getText().toString(),editText.getText().toString(),editTextPrice.getText().toString(),editTextQuantity.getText().toString());
//                        if (isUpdate == true){
//                            Toast.makeText(MainActivity.this, "Update Successful!", Toast.LENGTH_LONG).show();
//                        }
//                        else  Toast.makeText(MainActivity.this, "Update Failed!", Toast.LENGTH_LONG).show();
//                    }
//                }
//                );
//    }
//    public  void DeleteData() {
//        buttonDelete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Integer deletedrow = myDB.deleteData(editTextId.getText().toString());
//                        if (deletedrow > 0)
//                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }


}