package com.example.FrontEnd_PG4.request;

import android.content.Context;
import com.example.FrontEnd_PG4.util.Property;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 2/25/2015.
 */
public class RegisterRequest extends BaseRequest {
    private String firstName, lastName, email, password;
    private Property property;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterRequest(Context ctx, JsonHandler jsonHandler) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpPut(property.getProperty("serveraddr") + "login/register?email=" + email +
                "&password=" + password + "&firstName=" + firstName + "&lastName=" + lastName);
    }
}
