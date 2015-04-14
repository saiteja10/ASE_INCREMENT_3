package com.example.FrontEnd_PG4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.FrontEnd_PG4.R;
import com.example.FrontEnd_PG4.beans.JobBean;

import java.util.List;

/**
 * Created by Gurrala on 4/6/2015.
 */
public class JobAdapter extends BaseAdapter {
    private List<JobBean> jobBeans;
    private Context context;

    public JobAdapter(Context context, List<JobBean> jobBeans) {
        this.context = context;
        this.jobBeans = jobBeans;
    }

    @Override
    public int getCount() {
        return jobBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return jobBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolderItem {
        TextView total_amount, job_title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.job_view, parent, false);
            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.total_amount = (TextView) convertView.findViewById(R.id.total_amount);
            viewHolder.job_title = (TextView) convertView.findViewById(R.id.job_title);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.total_amount.setText("$ " + jobBeans.get(position).getTotalAmount());
        viewHolder.job_title.setText(jobBeans.get(position).getUserName());
        return convertView;
    }
}
