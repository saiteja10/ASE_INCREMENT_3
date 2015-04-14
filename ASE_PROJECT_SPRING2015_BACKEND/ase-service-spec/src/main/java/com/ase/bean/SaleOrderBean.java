package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gurrala on 4/5/2015.
 */
@XmlRootElement(namespace = "ase")
public class SaleOrderBean extends BaseBean {
    private Double totalAmount;
    private String userName;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
