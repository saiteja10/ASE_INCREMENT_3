package com.ase.bean.list;

import com.ase.bean.OrderBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 4/5/2015.
 */
@XmlRootElement(namespace = "ase")
public class OrderBeanList {
    private Long userId;
    private List<OrderBean> orderBeans = new ArrayList<OrderBean>();
    private Double totalAmount;

    @XmlElement
    public List<OrderBean> getOrderBeans() {
        return orderBeans;
    }

    public void setOrderBeans(List<OrderBean> orderBeans) {
        this.orderBeans = orderBeans;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
