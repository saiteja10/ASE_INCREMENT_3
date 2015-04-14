package com.ase.dao;

import com.ase.domain.Category;
import com.ase.domain.Item;
import com.ase.domain.SubCategory;
import com.ase.query.CategoryQuery;
import com.ase.query.ItemQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Component
public class ItemDAO extends BaseDAO<Item, ItemQuery> {
    @Override
    protected Class<Item> getEntityClass() {
        return Item.class;
    }

    public List<Item> getItems(SubCategory subCategory) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("subCategory", subCategory));
        return criteria.list();
    }
}
