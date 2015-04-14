package com.ase.domain;

import javax.persistence.*;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Entity
@Table(name = "restaurant_subcategory")
@PrimaryKeyJoinColumn(name = "id")
public class RestaurantSubCategoryMapping extends Base {
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "subCategoryId", nullable = false)
    private SubCategory subCategory;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "restaurantId", nullable = false)
    private Restaurant restaurant;

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
