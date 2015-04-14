package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.List;

/**
 * Created by Gurrala on 4/6/2015.
 */
public class PlaceOrderRequest extends BaseRequest {
    private Property property;
    private double totalAmount;
    private List<Category> categories;

    public PlaceOrderRequest(Context ctx, JsonHandler jsonHandler, double totalAmount, List<Category> categories) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.totalAmount = totalAmount;
        this.categories = categories;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        String urlParams = property.getProperty("serveraddr") + "order/place?userId=" + property.getProperty("userId")
                + "&totalAmount=" + totalAmount;
        for (Category category : categories) {
            urlParams += "&orderBeans.itemId=" + category.getId();
            urlParams += "&orderBeans.quantity=" + category.getQuantity();
            urlParams += "&orderBeans.subTotal=" + category.getQuantity() * category.getPrice();
        }
        return new HttpPut(urlParams);
    }
}
