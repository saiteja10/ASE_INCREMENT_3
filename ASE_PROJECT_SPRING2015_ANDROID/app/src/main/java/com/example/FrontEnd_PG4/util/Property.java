package com.example.FrontEnd_PG4.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gurrala on 2/24/2015.
 */
public class Property {
    private SharedPreferences sharedpreferences;

    public Property(Context context){
        sharedpreferences = context.getSharedPreferences("ase", Context.MODE_PRIVATE);
    }

    public String getProperty(String key) {
        return sharedpreferences.getString(key,"");
    }

    public void setProperty(String key, String value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
