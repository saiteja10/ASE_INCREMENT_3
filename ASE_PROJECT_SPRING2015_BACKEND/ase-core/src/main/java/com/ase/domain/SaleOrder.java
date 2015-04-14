package com.ase.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Suresh
 * Date: 4/17/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "sale_order")
@PrimaryKeyJoinColumn(name = "id")
public class SaleOrder extends Base {
    private Double totalAmount;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
