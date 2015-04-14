package com.example.FrontEnd_PG4.util;

import android.content.Context;

import com.example.FrontEnd_PG4.database.DBHelper;

/**
 * Created by Gurrala on 3/17/2015.
 */
public class CountHolder {
    private static int count = -1;

    public static int getCount(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        count = dbHelper.getCount();
        dbHelper.close();
        return count;
    }

    public static void setCount(int count) {
        CountHolder.count = count;
    }
}
