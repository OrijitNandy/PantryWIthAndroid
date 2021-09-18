package com.example.shopaid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "myDatabase.sqlite";
    public static final String PANTRY_TABLE = "pantry_list";
    public static final String SHOPPING_LIST_TABLE = "shopping_list";
    public static final String SHOPPING_LIST_ITEM_TABLE = "shopping_list_item";

    public static final String ID = "id";
    public static final String PANTRY_ITEM_ID = "pantry_item_id";
    public static final String SHOPPING_LIST_ID = "shopping_list_id";

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_ADDRESS = "shop_address";
    public static final String DATE = "date";


    SQLiteDatabase db;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
//        db.getPath();
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + PANTRY_TABLE +
                " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT," + 
                    PRICE + " FLOAT," + 
                    QUANTITY + " TEXT" +
                ")";
        String createTable1 = "CREATE TABLE " + SHOPPING_LIST_TABLE +
                " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SHOP_NAME + " TEXT," + 
                    SHOP_ADDRESS + " TEXT," + 
                    DATE + " DATE" +
                ")";
        String createTable2 = "CREATE TABLE " + SHOPPING_LIST_ITEM_TABLE +
                " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PANTRY_ITEM_ID + " INTEGER, " +
                    SHOPPING_LIST_ID + " INTEGER " +
                    
//                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                db.rawQuery("SELECT ID FROM " + PANTRY_TABLE,null) + " TEXT" + db.rawQuery("SELECT ID FROM" + PANTRY_TABLE1,null) + "TEXT" +
                ")";

        db.execSQL(createTable);
        db.execSQL(createTable1);
        db.execSQL(createTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + PANTRY_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE);
        db.execSQL(" DROP TABLE IF EXISTS " + SHOPPING_LIST_ITEM_TABLE);
        onCreate(db);
        onCreate(db);
        onCreate(db);
    }

//    public boolean addData(String name, String price, String quantity ){
////        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NAME,name);
//        contentValues.put(PRICE, price);
//        contentValues.put(QUANTITY, quantity);
//
//        long result = db.insert(PANTRY_TABLE, null , contentValues);
//
//        if(result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }

//    }

    public Cursor getAllData() {
        ArrayList<Pantry> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + PANTRY_TABLE;
//        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
     public Cursor getAllData2() {
         ArrayList<Shoplist> arrayList1= new ArrayList<>();
         String selectQuery2 = "SELECT * FROM " + SHOPPING_LIST_TABLE;
         Cursor cursor1 = db.rawQuery(selectQuery2,null);
         return cursor1;


     }


//        if (cursor.moveToNext()) {
//
//            do {
//                Pantry pantry = new Pantry(
//                        cursor.getInt(cursor.getColumnIndex(ID)),
//                        cursor.getString(cursor.getColumnIndex(NAME)),
//                        cursor.getFloat(cursor.getColumnIndex(PRICE))
//                );
//                arrayList.add(pantry);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return arrayList;


//    public Cursor getListContents(){
//        this.getWritableDatabase();
//        SQLiteDatabase db;
//        db = getWritableDatabase();
//
////        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, DATABASE_NAME, null, 1);
////        Cursor data = sqLiteHelper.getData("SELECT * FROM " + PANTRY_TABLE);
//        return db.rawQuery("SELECT * FROM " + PANTRY_TABLE,null);
//    }
    void updateData(String id, String name,String price, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(PRICE, price);
        contentValues.put(QUANTITY, quantity);
       long result = db.update(PANTRY_TABLE , contentValues , "id = ?" , new String[]{id});

       if (result == -1) {
           Toast.makeText(context, "Update Failed!", Toast.LENGTH_LONG).show();
       } else {
           Toast.makeText(context, "Update Successful!", Toast.LENGTH_LONG).show();
       }

    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(PANTRY_TABLE , "ID = ?" , new String[]{id});
    }
}