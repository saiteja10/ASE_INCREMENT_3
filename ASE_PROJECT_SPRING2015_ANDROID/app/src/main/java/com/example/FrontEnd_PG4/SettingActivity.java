package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.FrontEnd_PG4.util.Property;

/**
 * Created by Gurrala on 2/24/2015.
 */
public class SettingActivity extends Activity {
    private Property property;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        property = new Property(this);
        editText = (EditText) findViewById(R.id.serveraddr);
        editText.setText(property.getProperty("serveraddr"));
    }

    public void onUpdate(View v) {
        property.setProperty("serveraddr", editText.getText().toString());
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
    }
}
