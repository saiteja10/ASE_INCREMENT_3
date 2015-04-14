package com.example.FrontEnd_PG4.request;

import android.content.Context;
import com.example.FrontEnd_PG4.util.Property;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 2/24/2015.
 */
public class LoginRequest extends BaseRequest {
    private String email, password;
    private Property property;
    private boolean isEmployee;

    public LoginRequest(Context ctx, JsonHandler jsonHandler, String email, String password, boolean isEmployee) {
        super(ctx, jsonHandler);
        this.email = email;
        this.password = password;
        this.isEmployee = isEmployee;
        property = new Property(ctx);
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpPost(property.getProperty("serveraddr") + "login?username=" + email +
                "&password=" + password + "&isEmployee=" + isEmployee);
    }
}
