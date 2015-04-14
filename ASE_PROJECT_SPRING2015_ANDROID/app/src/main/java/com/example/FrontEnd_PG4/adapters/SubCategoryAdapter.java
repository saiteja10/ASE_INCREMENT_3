package com.example.FrontEnd_PG4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.FrontEnd_PG4.R;
import com.example.FrontEnd_PG4.beans.SubCategory;
import com.example.FrontEnd_PG4.beans.SubCategoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class SubCategoryAdapter extends BaseAdapter implements Filterable {
    private List<SubCategory> subCategories;
    private Context context;
    private ValueFilter valueFilter;
    private SubCategoryList subCategoryList;

    public SubCategoryAdapter(Context context, SubCategoryList subCategoryList) {
        this.context = context;
        this.subCategories = subCategoryList.getSubCategories();
        this.subCategoryList = subCategoryList;
    }

    @Override
    public SubCategory getItem(int position) {
        return subCategoryList.getSubCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return subCategoryList.getSubCategories().size();
    }

    class ViewHolderItem {
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_list, parent, false);
            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.category_name);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.textView.setText(subCategoryList.getSubCategories().get(position).getName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<SubCategory> filterList = new ArrayList<SubCategory>();
                for (int i = 0; i < subCategories.size(); i++) {
                    if (subCategories.get(i).getName().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) {
                        SubCategory category = new SubCategory();
                        category.setDescription(subCategories.get(i).getDescription());
                        category.setId(subCategories.get(i).getId());
                        category.setName(subCategories.get(i).getName());
                        filterList.add(category);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = subCategories.size();
                results.values = subCategories;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            subCategoryList.setSubCategories((ArrayList<SubCategory>) results.values);
            notifyDataSetChanged();
        }
    }
}
