package com.ase.dao;

import com.ase.domain.OrderStatus;
import com.ase.domain.SaleOrder;
import com.ase.query.SaleOrderQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Component
public class SaleOrderDAO extends BaseDAO<SaleOrder, SaleOrderQuery> {
    @Override
    protected Class<SaleOrder> getEntityClass() {
        return SaleOrder.class;
    }

    public List<SaleOrder> getOpenedOrders() {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("orderStatus", OrderStatus.PLACED));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
