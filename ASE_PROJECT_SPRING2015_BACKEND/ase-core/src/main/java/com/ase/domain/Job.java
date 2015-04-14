package com.ase.domain;

import javax.persistence.*;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Entity
@Table(name = "job")
@PrimaryKeyJoinColumn(name = "id")
public class Job extends Base {
    private String title;
    private Boolean enabled;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employeeId")
    private Employee employee;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "saleOrderId")
    private SaleOrder saleOrder;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }
}
