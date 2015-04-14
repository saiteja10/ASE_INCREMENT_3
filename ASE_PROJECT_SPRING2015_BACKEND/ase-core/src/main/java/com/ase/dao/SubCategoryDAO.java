package com.ase.dao;

import com.ase.domain.Category;
import com.ase.domain.SubCategory;
import com.ase.query.CategoryQuery;
import com.ase.query.SubCategoryQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Component
public class SubCategoryDAO extends BaseDAO<SubCategory, SubCategoryQuery> {
    @Override
    protected Class<SubCategory> getEntityClass() {
        return SubCategory.class;
    }

    public List<SubCategory> getByCategoryId(Category category) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("category", category));
        return criteria.list();
    }
}
