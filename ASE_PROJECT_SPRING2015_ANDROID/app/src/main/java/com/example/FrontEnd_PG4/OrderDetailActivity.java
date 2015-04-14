package com.example.FrontEnd_PG4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FrontEnd_PG4.adapters.OrderDetailAdapter;
import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.request.AcceptOrderRequest;
import com.example.FrontEnd_PG4.request.GetOrderRequest;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.util.Property;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends ActionBarActivity implements JsonHandler {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_activity);
        listView = (ListView) findViewById(R.id.item_detail_list);
        TextView textView = (TextView) findViewById(R.id.grand_total);
        textView.setText("$" + getIntent().getDoubleExtra("total", 0.0));
        Long saleOrderId = getIntent().getLongExtra("saleOrderId", 1);
        GetOrderRequest request = new GetOrderRequest(this, this, saleOrderId);
        request.execute();
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    public void onAccept(View v) {
        AcceptOrderRequest request = new AcceptOrderRequest(this, new JsonHandler() {
            @Override
            public void parseJson(String jsonResult) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonResult);
                    if (jsonObject.getBoolean("success")) {
                        Toast.makeText(OrderDetailActivity.this, "Accepted", Toast.LENGTH_LONG).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", getIntent().getLongExtra("saleOrderId", 1));
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    } else
                        Toast.makeText(OrderDetailActivity.this, "Could not accept", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, getIntent().getLongExtra("saleOrderId", 1));
        request.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray array = jsonObject.getJSONArray("itemBeans");
            List<Category> categories = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject categoryBean = array.getJSONObject(i);
                Category category = new Category();
                category.setPrice(categoryBean.getDouble("price"));
                category.setName(categoryBean.getString("name"));
                category.setDescription(categoryBean.getString("description"));
                category.setQuantity((int) categoryBean.getLong("quantity"));
                category.setId(categoryBean.getLong("id"));
                categories.add(category);
            }
            OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this, categories);
            listView.setAdapter(orderDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
