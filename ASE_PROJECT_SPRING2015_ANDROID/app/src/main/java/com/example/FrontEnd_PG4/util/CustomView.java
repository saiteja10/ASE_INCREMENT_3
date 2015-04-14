package com.example.FrontEnd_PG4.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.FrontEnd_PG4.R;

/**
 * Created by Gurrala on 4/5/2015.
 */
public class CustomView {
    public static View setImageText(Context context, ActionBar actionBar, int count){
        LayoutInflater li = LayoutInflater.from(context);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#343635")));
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.END
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        View theview = li.inflate(R.layout.img, null);
        TextView textView = (TextView) theview.findViewById(R.id.myImageViewText);
        textView.setText(count + "");
        theview.setLayoutParams(layoutParams);
        return theview;
    }
}
