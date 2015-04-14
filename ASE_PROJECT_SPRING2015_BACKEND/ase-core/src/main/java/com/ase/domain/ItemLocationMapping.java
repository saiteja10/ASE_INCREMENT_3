package com.ase.domain;

import javax.persistence.*;

/**
 * Created by Gurrala on 2/22/2015.
 */
@Entity
@Table(name = "item_location_mapping")
@PrimaryKeyJoinColumn(name = "id")
public class ItemLocationMapping extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemLocationId", nullable = false)
    private ItemLocation itemLocation;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemLocation getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(ItemLocation itemLocation) {
        this.itemLocation = itemLocation;
    }
}
