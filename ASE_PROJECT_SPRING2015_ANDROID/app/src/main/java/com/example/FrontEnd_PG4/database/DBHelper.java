package com.example.FrontEnd_PG4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.beans.CategoryList;

import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    // User Database
    public static final String DATABASE_NAME = "orders";
    public static final String TABLE = "order_table";
    public static final String _ID = "id";
    public static final String ITEM_NAME = "item_name";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String ITEM_DESC = "item_desc";
    public static final String TOT_PRICE = "tot_price";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ITEM_NAME + " TEXT," + QUANTITY + " INTEGER,"
            + PRICE + " DOUBLE," + TOT_PRICE + " DOUBLE," + ITEM_DESC + " TEXT" + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertItem(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        if (isItemExists(category, db)) {
            incrementItem(category, db);
            db.close();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, category.getName());
        values.put(ITEM_DESC, category.getDescription());
        values.put(PRICE, category.getPrice());
        values.put(QUANTITY, 1);
        values.put(TOT_PRICE, category.getPrice());
        db.insert(TABLE, null, values);
        db.close();
    }

    private void incrementItem(Category category, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, category.getName());
        values.put(ITEM_DESC, category.getDescription());
        values.put(PRICE, category.getPrice());
        int quantity = getQuantity(category, db) + 1;
        values.put(QUANTITY, quantity);
        values.put(TOT_PRICE, category.getPrice() * quantity);
        db.update(TABLE, values, _ID + "=" + category.getId(), null);
    }

    public void updateItem(Category category, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, category.getName());
        values.put(ITEM_DESC, category.getDescription());
        values.put(PRICE, category.getPrice());
        values.put(QUANTITY, quantity);
        values.put(TOT_PRICE, category.getPrice() * quantity);
        db.update(TABLE, values, _ID + "=" + category.getId(), null);
        db.close();
    }

    public void deleteItem(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, _ID + "=" + category.getId(), null);
        db.close();
    }

    public CategoryList getItems() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(true, TABLE, new String[]{
                        _ID, ITEM_NAME, ITEM_DESC, PRICE, QUANTITY}, null,
                null, null, null, null, null);
        CategoryList categoryList = new CategoryList();
        Double tot_price = 0.0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Category category = new Category();
                category.setName(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                category.setId((long) cursor.getInt(cursor.getColumnIndex(_ID)));
                category.setDescription(cursor.getString(cursor.getColumnIndex(ITEM_DESC)));
                category.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
                category.setQuantity(cursor.getInt(cursor.getColumnIndex(QUANTITY)));
                tot_price += cursor.getInt(cursor.getColumnIndex(QUANTITY)) * cursor.getDouble(cursor.getColumnIndex(PRICE));
                categoryList.getCategories().add(category);
                while (cursor.moveToNext()) {
                    category = new Category();
                    category.setName(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                    category.setId((long) cursor.getInt(cursor.getColumnIndex(_ID)));
                    category.setDescription(cursor.getString(cursor.getColumnIndex(ITEM_DESC)));
                    category.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
                    category.setQuantity(cursor.getInt(cursor.getColumnIndex(QUANTITY)));
                    tot_price += cursor.getInt(cursor.getColumnIndex(QUANTITY)) * cursor.getDouble(cursor.getColumnIndex(PRICE));
                    categoryList.getCategories().add(category);
                }
            }
            cursor.close();
        }
        db.close();
        categoryList.setTot_price(tot_price);
        return categoryList;
    }

    public Double getTotal() {
        String sumQuery = "SELECT  SUM(" + TOT_PRICE + ") FROM " + TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sumQuery, null);
        Double tot_price = 0.0;
        if (cursor != null) {
            if (cursor.moveToFirst())
                tot_price = cursor.getDouble(0);
            tot_price = Math.round(tot_price * 100D) / 100D;
            cursor.close();
        }
        db.close();
        return tot_price;
    }

    public int getCount() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(true, TABLE, new String[]{
                        _ID, ITEM_NAME, ITEM_DESC, PRICE, QUANTITY}, null,
                null, null, null, null, null);
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;
    }

    private boolean isItemExists(Category category, SQLiteDatabase db) {
        Cursor cursor = db.query(true, TABLE, new String[]{
                        _ID, ITEM_NAME, ITEM_DESC, PRICE, QUANTITY}, _ID + "=" + category.getId(),
                null, null, null, null, null);
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        }
        return count > 0;
    }

    private int getQuantity(Category category, SQLiteDatabase db) {
        Cursor cursor = db.query(true, TABLE, new String[]{
                        QUANTITY}, _ID + "=" + category.getId(),
                null, null, null, null, null);
        int quantity = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
            }
            cursor.close();
        }
        return quantity;
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TABLE);
        db.close();
    }
}
