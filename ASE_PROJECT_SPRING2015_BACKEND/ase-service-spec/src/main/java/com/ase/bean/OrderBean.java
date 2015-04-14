package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gurrala on 4/5/2015.
 */
@XmlRootElement(namespace = "ase")
public class OrderBean extends BaseBean {
    private Long itemId, quantity;
    private Double subTotal;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
