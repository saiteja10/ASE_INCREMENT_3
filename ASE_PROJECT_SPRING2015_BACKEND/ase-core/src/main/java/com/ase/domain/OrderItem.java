package com.ase.domain;

import javax.persistence.*;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Entity
@Table(name = "order_item")
@PrimaryKeyJoinColumn(name = "id")
public class OrderItem extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saleOrderId", nullable = false)
    private SaleOrder saleOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;
    private Long quantity;
    private Double subTotal;

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
