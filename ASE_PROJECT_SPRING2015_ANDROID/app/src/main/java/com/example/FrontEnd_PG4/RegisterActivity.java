package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.request.RegisterRequest;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sai on 2/21/2015.
 */
public class RegisterActivity extends Activity implements JsonHandler {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onRegister(View v) {
        String fname = ((EditText)findViewById(R.id.fname)).getText().toString();
        String lname = ((EditText) findViewById(R.id.lname)).getText().toString();
        String email =  ((EditText)findViewById(R.id.remail)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.rpwd)).getText().toString();
        String cpwd =  ((EditText)findViewById(R.id.rcpwd)).getText().toString();
        if (validate(fname, lname, email, pwd, cpwd)) {
            RegisterRequest registerRequest = new RegisterRequest(this, this);
            registerRequest.setFirstName(fname);
            registerRequest.setLastName(lname);
            registerRequest.setEmail(email);
            registerRequest.setPassword(pwd);
            registerRequest.execute();
        }
    }

    private boolean validate(String fname, String lname, String email, String pwd, String cpwd) {
        if (fname.length() <= 0 || lname.length() <= 0) {
            Toast.makeText(this, "First name and Last name should not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (email.length() <= 0) {
            Toast.makeText(this, "Email should not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (pwd.length() <= 0 || cpwd.length() <= 0) {
            Toast.makeText(this, "Password should not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!pwd.equalsIgnoreCase(cpwd)) {
            Toast.makeText(this, "Passwords are not equal", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            if (jsonObject.getBoolean("success")) {
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(this, "Couldn't register at this time. Try again.", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}