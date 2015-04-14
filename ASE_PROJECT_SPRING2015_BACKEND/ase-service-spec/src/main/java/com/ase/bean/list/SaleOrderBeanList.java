package com.ase.bean.list;

import com.ase.bean.SaleOrderBean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 4/5/2015.
 */
@XmlRootElement(namespace = "ase")
public class SaleOrderBeanList {
    private List<SaleOrderBean> saleOrderBeans = new ArrayList<SaleOrderBean>();

    public List<SaleOrderBean> getSaleOrderBeans() {
        return saleOrderBeans;
    }

    public void setSaleOrderBeans(List<SaleOrderBean> saleOrderBeans) {
        this.saleOrderBeans = saleOrderBeans;
    }
}
