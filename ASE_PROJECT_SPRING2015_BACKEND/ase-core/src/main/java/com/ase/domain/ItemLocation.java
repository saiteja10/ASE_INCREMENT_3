package com.ase.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Gurrala on 2/22/2015.
 */
@Entity
@Table(name = "item_location")
@PrimaryKeyJoinColumn(name = "id")
public class ItemLocation extends Base {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
