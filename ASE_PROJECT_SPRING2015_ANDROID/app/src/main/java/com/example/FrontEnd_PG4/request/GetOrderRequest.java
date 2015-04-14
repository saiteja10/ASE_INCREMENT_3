package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 4/6/2015.
 */
public class GetOrderRequest extends BaseRequest {
    private Property property;
    private Long saleOrderId;

    public GetOrderRequest(Context ctx, JsonHandler jsonHandler, Long saleOrderId) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.saleOrderId = saleOrderId;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpGet(property.getProperty("serveraddr") + "order/" + saleOrderId);
    }
}
