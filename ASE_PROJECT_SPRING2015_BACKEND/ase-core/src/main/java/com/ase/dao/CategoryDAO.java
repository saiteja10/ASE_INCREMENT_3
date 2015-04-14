package com.ase.dao;

import com.ase.domain.Category;
import com.ase.query.CategoryQuery;
import org.springframework.stereotype.Component;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Component
public class CategoryDAO extends BaseDAO<Category, CategoryQuery> {
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
