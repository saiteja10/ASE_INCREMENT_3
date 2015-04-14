package com.example.FrontEnd_PG4.request;

import android.content.Context;

import com.example.FrontEnd_PG4.util.Property;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class SubCategoryRequest extends BaseRequest {
    private Property property;
    private Long categoryId;

    public SubCategoryRequest(Context ctx, JsonHandler jsonHandler, Long categoryId) {
        super(ctx, jsonHandler);
        property = new Property(ctx);
        this.categoryId = categoryId;
    }

    @Override
    protected HttpRequestBase getHttpRequest() {
        return new HttpGet(property.getProperty("serveraddr") + "catalog/subcategory/" + categoryId);
    }
}
