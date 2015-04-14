package com.example.FrontEnd_PG4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.FrontEnd_PG4.R;
import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.beans.CategoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class CategoryAdapter extends BaseAdapter implements Filterable {
    private List<Category> categories;
    private Context context;
    private ValueFilter valueFilter;
    private CategoryList categoryList;

    public CategoryAdapter(Context context, CategoryList categoryList) {
        this.context = context;
        this.categories = categoryList.getCategories();
        this.categoryList = categoryList;
    }

    @Override
    public Category getItem(int position) {
        return categoryList.getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return categoryList.getCategories().size();
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
        viewHolder.textView.setText(categoryList.getCategories().get(position).getName());
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
                List<Category> filterList = new ArrayList<Category>();
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getName().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) {
                        Category category = new Category();
                        category.setDescription(categories.get(i).getDescription());
                        category.setId(categories.get(i).getId());
                        category.setName(categories.get(i).getName());
                        filterList.add(category);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = categories.size();
                results.values = categories;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categoryList.setCategories((ArrayList<Category>) results.values);
            notifyDataSetChanged();
        }
    }
}
