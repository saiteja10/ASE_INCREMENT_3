package com.ase.handler;

import com.ase.bean.ItemBean;
import com.ase.bean.OrderBean;
import com.ase.bean.SaleOrderBean;
import com.ase.bean.list.ItemBeanList;
import com.ase.bean.list.OrderBeanList;
import com.ase.bean.list.SaleOrderBeanList;
import com.ase.dao.*;
import com.ase.domain.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Component
public class OrderHandler {
    @Inject
    OrderDAO orderDAO;
    @Inject
    SaleOrderDAO saleOrderDAO;
    @Inject
    JobDAO jobDAO;
    @Inject
    UserDAO userDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    ItemDAO itemDAO;

    public void placeOrder(OrderBeanList orderBeanList) throws Exception {
        SaleOrder saleOrder = new SaleOrder();
        User user = userDAO.findByID(orderBeanList.getUserId());
        if (user == null)
            throw new Exception("Invalid User");
        saleOrder.setUser(user);
        saleOrder.setOrderStatus(OrderStatus.PLACED);
        saleOrder.setTotalAmount(orderBeanList.getTotalAmount());
        saleOrderDAO.save(saleOrder);
        for (OrderBean orderBean : orderBeanList.getOrderBeans()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSaleOrder(saleOrder);
            orderItem.setItem(itemDAO.findByID(orderBean.getItemId()));
            orderItem.setQuantity(orderBean.getQuantity());
            orderItem.setSubTotal(orderBean.getSubTotal());
            orderDAO.save(orderItem);
        }
    }

    public SaleOrderBeanList getOpenedOrders() {
        SaleOrderBeanList saleOrderBeanList = new SaleOrderBeanList();
        List<SaleOrder> openedOrders = saleOrderDAO.getOpenedOrders();
        for (SaleOrder saleOrder : openedOrders) {
            saleOrderBeanList.getSaleOrderBeans().add(getSaleOrderBean(saleOrder));
        }
        return saleOrderBeanList;
    }

    private SaleOrderBean getSaleOrderBean(SaleOrder saleOrder) {
        SaleOrderBean saleOrderBean = new SaleOrderBean();
        saleOrderBean.setId(saleOrder.getId());
        saleOrderBean.setTotalAmount(saleOrder.getTotalAmount());
        saleOrderBean.setUserName(saleOrder.getUser().getFirstName());
        return saleOrderBean;
    }

    public void acceptOrder(Long saleOrderId, Long employeeId) throws Exception {
        SaleOrder saleOrder = saleOrderDAO.findByID(saleOrderId);
        if (saleOrder == null)
            throw new Exception("Invalid Sale Order");
        Employee employee = employeeDAO.findByID(employeeId);
        if (employee == null)
            throw new Exception("Invalid Employee");
        saleOrder.setOrderStatus(OrderStatus.ACCEPTED);
        Job job = new Job();
        job.setEmployee(employee);
        job.setEnabled(true);
        job.setSaleOrder(saleOrder);
        job.setTitle("New Job");
        jobDAO.save(job);
    }

    public ItemBeanList getOrder(Long saleOrderId) throws Exception {
        SaleOrder saleOrder = saleOrderDAO.findByID(saleOrderId);
        if (saleOrder == null)
            throw new Exception("Invalid Sale Order");
        ItemBeanList itemBeanList = new ItemBeanList();
        List<OrderItem> orderItems = orderDAO.getBySaleOrder(saleOrder);
        for (OrderItem orderItem : orderItems) {
            itemBeanList.getItemBeans().add(getOrderItemBean(orderItem));
        }
        return itemBeanList;
    }

    private ItemBean getOrderItemBean(OrderItem orderItem) {
        ItemBean itemBean = new ItemBean();
        itemBean.setId(orderItem.getItem().getId());
        itemBean.setDescription(orderItem.getItem().getDescription());
        itemBean.setName(orderItem.getItem().getName());
        itemBean.setPrice(orderItem.getItem().getPrice());
        itemBean.setSubCategoryId(orderItem.getItem().getSubCategory().getId());
        itemBean.setQuantity(orderItem.getQuantity());
        itemBean.setTotal(orderItem.getSubTotal());
        return itemBean;
    }
}
