package com.example.FrontEnd_PG4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.FrontEnd_PG4.R;
import com.example.FrontEnd_PG4.beans.Category;

import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class OrderDetailAdapter extends BaseAdapter {
    private List<Category> categories;
    private Context context;

    public OrderDetailAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    class ViewHolderItem {
        TextView item_name, item_price, item_total, item_quantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item_list_view, parent, false);
            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.item_quantity = (TextView) convertView.findViewById(R.id.item_quantity);
            viewHolder.item_price = (TextView) convertView.findViewById(R.id.item_price);
            viewHolder.item_total = (TextView) convertView.findViewById(R.id.item_total);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        Category category = categories.get(position);
        viewHolder.item_name.setText(category.getName());
        viewHolder.item_price.setText(category.getPrice() + "");
        viewHolder.item_total.setText(category.getQuantity() * category.getPrice() + "");
        viewHolder.item_quantity.setText(category.getQuantity() + "");
        return convertView;
    }
}
