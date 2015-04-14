package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.request.LoginRequest;
import com.example.FrontEnd_PG4.util.Property;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity implements JsonHandler {
    private EditText email, password;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ImageButton register = (ImageButton) findViewById(R.id.imageButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void onLogin(View v) {
        String emailTxt = email.getText().toString();
        String pwdTxt = password.getText().toString();
        if (emailTxt.length() > 0 && pwdTxt.length() > 0) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.isEmployee);
            boolean isEmployee = checkBox.isChecked();
            LoginRequest loginRequest = new LoginRequest(this, this, emailTxt, pwdTxt, isEmployee);
            loginRequest.execute();
        } else
            Toast.makeText(this, "Enter valid credentials", Toast.LENGTH_LONG).show();
    }

    public void onSetting(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            if (jsonObject.getBoolean("success")) {
                Intent intent;
                if (jsonObject.getString("type").equalsIgnoreCase("user"))
                    intent = new Intent(this, CategoryActivity.class);
                else if (jsonObject.getString("type").equalsIgnoreCase("employee"))
                    intent = new Intent(this, EmployeeDashboard.class);
                else
                    intent = new Intent(this, AdminDashboard.class);
                new Property(this).setProperty("userId", jsonObject.getLong("id") + "");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
