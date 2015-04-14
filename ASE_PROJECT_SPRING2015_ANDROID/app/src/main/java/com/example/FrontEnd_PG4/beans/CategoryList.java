package com.example.FrontEnd_PG4.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class CategoryList {
    private List<Category> categories = new ArrayList<>();
    private Double tot_price;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Double getTot_price() {
        return tot_price;
    }

    public void setTot_price(Double tot_price) {
        this.tot_price = tot_price;
    }
}
