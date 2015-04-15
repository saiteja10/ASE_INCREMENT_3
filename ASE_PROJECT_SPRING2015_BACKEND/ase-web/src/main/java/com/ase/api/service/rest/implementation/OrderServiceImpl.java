package com.ase.api.service.rest.implementation;

import com.ase.api.aop.MyTransaction;
import com.ase.api.service.OrderService;
import com.ase.bean.BaseBean;
import com.ase.bean.SaleOrderBean;
import com.ase.bean.list.ItemBeanList;
import com.ase.bean.list.OrderBeanList;
import com.ase.bean.list.SaleOrderBeanList;
import com.ase.handler.OrderHandler;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by sai on 4/5/2015.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderHandler orderHandler;

    @MyTransaction
    @Override
    public ItemBeanList getOrder(Long saleOrderId) throws Exception {
        return orderHandler.getOrder(saleOrderId);
    }

    @MyTransaction
    @Override
    public BaseBean placeOrder(OrderBeanList orderBeanList) throws Exception {
        orderHandler.placeOrder(orderBeanList);
        return new BaseBean();
    }

    @MyTransaction
    @Override
    public SaleOrderBeanList getOpenedOrders() {
        return orderHandler.getOpenedOrders();
    }

    @MyTransaction
    @Override
    public BaseBean acceptOrder(Long saleOrderId, Long employeeId) throws Exception {
        orderHandler.acceptOrder(saleOrderId, employeeId);
        return new BaseBean();
    }
}
