package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 4/6/2015.
 */
public class AcceptOrderRequest extends BaseRequest {
    private Property property;
    private Long saleOrderId;

    public AcceptOrderRequest(Context ctx, JsonHandler jsonHandler, Long saleOrderId) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.saleOrderId = saleOrderId;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpPost(property.getProperty("serveraddr") + "order/accept/" + saleOrderId + "/" + property.getProperty("userId"));
    }
}
