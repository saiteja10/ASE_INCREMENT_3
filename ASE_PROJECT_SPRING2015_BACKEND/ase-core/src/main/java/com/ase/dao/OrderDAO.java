package com.ase.dao;

import com.ase.domain.OrderItem;
import com.ase.domain.SaleOrder;
import com.ase.query.OrderQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Component
public class OrderDAO extends BaseDAO<OrderItem, OrderQuery> {
    @Override
    protected Class<OrderItem> getEntityClass() {
        return OrderItem.class;
    }

    public List<OrderItem> getBySaleOrder(SaleOrder saleOrder) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("saleOrder", saleOrder));
        return criteria.list();
    }
}
