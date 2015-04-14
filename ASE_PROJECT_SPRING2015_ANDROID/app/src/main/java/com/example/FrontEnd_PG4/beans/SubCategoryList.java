package com.example.FrontEnd_PG4.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
public class SubCategoryList {
    private List<SubCategory> subCategories = new ArrayList<>();

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
