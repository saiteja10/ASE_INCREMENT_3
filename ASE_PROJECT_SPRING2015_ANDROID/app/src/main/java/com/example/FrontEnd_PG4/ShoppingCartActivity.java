package com.example.FrontEnd_PG4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.FrontEnd_PG4.adapters.ShoppingCartAdapter;
import com.example.FrontEnd_PG4.beans.CategoryList;
import com.example.FrontEnd_PG4.database.DBHelper;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.request.PlaceOrderRequest;
import com.example.FrontEnd_PG4.util.CountHolder;
import com.example.FrontEnd_PG4.util.CustomView;

import org.json.JSONException;
import org.json.JSONObject;

public class ShoppingCartActivity extends ActionBarActivity {
    private ListView listView;
    private TextView total;
    private CategoryList categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cartactivity);
        listView = (ListView) findViewById(R.id.orderList);
        total = (TextView) findViewById(R.id.total);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(CustomView.setImageText(this, actionBar, CountHolder.getCount(this)));
        DBTask dbTask = new DBTask(this);
        dbTask.execute("");
    }

    public void updateActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(CustomView.setImageText(this, actionBar, CountHolder.getCount(this)));
    }

    class DBTask extends AsyncTask<String, String, String> {
        private Context context;
        private ProgressDialog progressDialog;

        public DBTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            DBHelper dbHelper = new DBHelper(context);
            categoryList = dbHelper.getItems();
            String total = dbHelper.getTotal() + "";
            dbHelper.close();
            return total;
        }

        @Override
        protected void onPostExecute(String s) {
            updateView(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "please wait....", true);
        }
    }

    public void onShoppingBag(View v) {
    }

    public void updateView(String s) {
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, categoryList);
        listView.setAdapter(shoppingCartAdapter);
        total.setText(s);
//        listView.setOnItemClickListener(this);
    }

    public void onPayment(View v) {
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest(this, new JsonHandler() {
            @Override
            public void parseJson(String jsonResult) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonResult);
                    if (jsonObject.getBoolean("success")) {
                        Toast.makeText(ShoppingCartActivity.this, "Successfully Placed Order", Toast.LENGTH_LONG).show();
                        DBHelper dbHelper = new DBHelper(ShoppingCartActivity.this);
                        dbHelper.clear();
                        dbHelper.close();
                        finish();
                    } else
                        Toast.makeText(ShoppingCartActivity.this, "Couldn't Place Order at this time. Try again.", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, Double.parseDouble(total.getText().toString()), categoryList.getCategories());
        placeOrderRequest.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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
}
