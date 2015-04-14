package com.ase.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Suresh
 * Date: 4/17/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "item_category")
@PrimaryKeyJoinColumn(name = "id")
public class Category extends Base {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
