package com.example.FrontEnd_PG4.beans;


/**
 * Created by Gurrala on 4/5/2015.
 */
public class JobBean {
    private Double totalAmount;
    private String userName;
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
