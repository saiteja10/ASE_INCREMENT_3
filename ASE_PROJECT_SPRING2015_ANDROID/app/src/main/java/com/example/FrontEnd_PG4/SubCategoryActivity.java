package com.example.FrontEnd_PG4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.FrontEnd_PG4.adapters.SubCategoryAdapter;
import com.example.FrontEnd_PG4.beans.SubCategory;
import com.example.FrontEnd_PG4.beans.SubCategoryList;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.request.SubCategoryRequest;
import com.example.FrontEnd_PG4.util.CountHolder;
import com.example.FrontEnd_PG4.util.CustomView;

import org.json.JSONArray;
import org.json.JSONObject;

public class SubCategoryActivity extends ActionBarActivity implements JsonHandler, AdapterView.OnItemClickListener {
    private ListView listView;
    private SubCategoryList subCategoryList;
    private SubCategoryAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        listView = (ListView) findViewById(R.id.itemList);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(CustomView.setImageText(this, actionBar, CountHolder.getCount(this)));
        EditText editText = (EditText) findViewById(R.id.searchTxt);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (subCategoryAdapter != null) {
                    subCategoryAdapter.getFilter().filter(s);
                }
            }
        });
        SubCategoryRequest subCategoryRequest = new SubCategoryRequest(this, this, getIntent().getLongExtra("categoryId", 1));
        subCategoryRequest.execute();
    }

    @Override
    protected void onResume() {
        getSupportActionBar().setCustomView(CustomView.setImageText(this, getSupportActionBar(), CountHolder.getCount(this)));
        super.onResume();
    }

    public void onShoppingBag(View v) {
        try {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_category, menu);
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
            JSONArray array = jsonObject.getJSONArray("subCategoryBeans");
            subCategoryList = new SubCategoryList();
            for (int i = 0; i < array.length(); i++) {
                JSONObject subCategoryBean = array.getJSONObject(i);
                SubCategory subCategory = new SubCategory();
                subCategory.setDescription(subCategoryBean.getString("description"));
                subCategory.setName(subCategoryBean.getString("name"));
                subCategory.setId(subCategoryBean.getLong("id"));
                subCategoryList.getSubCategories().add(subCategory);
            }
            subCategoryAdapter = new SubCategoryAdapter(this, subCategoryList);
            listView.setAdapter(subCategoryAdapter);
            listView.setOnItemClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("subCategoryId", subCategoryList.getSubCategories().get(position).getId());
        startActivity(intent);
    }
}
