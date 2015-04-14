package com.ase.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Entity
@Table(name = "restaurant")
@PrimaryKeyJoinColumn(name = "id")
public class Restaurant extends Base {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
