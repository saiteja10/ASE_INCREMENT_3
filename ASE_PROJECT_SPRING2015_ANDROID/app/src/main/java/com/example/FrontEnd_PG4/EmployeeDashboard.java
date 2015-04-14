package com.example.FrontEnd_PG4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.FrontEnd_PG4.adapters.CategoryAdapter;
import com.example.FrontEnd_PG4.adapters.JobAdapter;
import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.beans.CategoryList;
import com.example.FrontEnd_PG4.beans.JobBean;
import com.example.FrontEnd_PG4.request.GetOpenedOrdersRequest;
import com.example.FrontEnd_PG4.request.JsonHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 2/25/2015.
 */
public class EmployeeDashboard extends Activity implements JsonHandler, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<JobBean> jobBeans;
    private JobAdapter jobAdapter;
    private int pos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_dashboard);
        listView = (ListView) findViewById(R.id.jobList);
        GetOpenedOrdersRequest request = new GetOpenedOrdersRequest(this, this);
        request.execute();
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray array = jsonObject.getJSONArray("saleOrderBeans");
            jobBeans = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject categoryBean = array.getJSONObject(i);
                JobBean jobBean = new JobBean();
                jobBean.setTotalAmount(categoryBean.getDouble("totalAmount"));
                jobBean.setUserName(categoryBean.getString("userName"));
                jobBean.setId(categoryBean.getLong("id"));
                jobBeans.add(jobBean);
            }
            jobAdapter = new JobAdapter(this, jobBeans);
            listView.setAdapter(jobAdapter);
            listView.setOnItemClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("saleOrderId", jobBeans.get(position).getId());
        intent.putExtra("total", jobBeans.get(position).getTotalAmount());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                jobBeans.remove(pos);
                jobAdapter.notifyDataSetChanged();
            }
        }
    }
}