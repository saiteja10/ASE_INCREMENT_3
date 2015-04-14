package com.ase.dao;

import com.ase.domain.RestaurantSubCategoryMapping;
import com.ase.domain.SubCategory;
import com.ase.query.RestaurantSubCategoryMappingQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Component
public class RestaurantSubCategoryMappingDAO extends BaseDAO<RestaurantSubCategoryMapping, RestaurantSubCategoryMappingQuery> {
    @Override
    protected Class<RestaurantSubCategoryMapping> getEntityClass() {
        return RestaurantSubCategoryMapping.class;
    }

    public List<String> getRestaurantsNameBySubCategory(SubCategory subCategory){
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("subCategory",subCategory));
        criteria.setProjection(Projections.groupProperty("restaurant"));
        return criteria.list();
    }
}
