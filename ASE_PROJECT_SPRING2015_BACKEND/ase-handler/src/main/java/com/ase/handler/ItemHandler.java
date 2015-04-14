package com.ase.handler;

import com.ase.bean.CategoryBean;
import com.ase.bean.ItemBean;
import com.ase.bean.SubCategoryBean;
import com.ase.dao.CategoryDAO;
import com.ase.dao.ItemDAO;
import com.ase.dao.RestaurantSubCategoryMappingDAO;
import com.ase.dao.SubCategoryDAO;
import com.ase.domain.Category;
import com.ase.domain.Item;
import com.ase.domain.SubCategory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Component
public class ItemHandler {
    @Inject
    CategoryDAO categoryDAO;
    @Inject
    SubCategoryDAO subCategoryDAO;
    @Inject
    ItemDAO itemDAO;
    @Inject
    RestaurantSubCategoryMappingDAO restaurantSubCategoryMappingDAO;

    public List<CategoryBean> getAllCategories() {
        List<Category> categories = categoryDAO.getAll();
        List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();
        for (Category category : categories)
            categoryBeans.add(getCategoryBean(category));
        return categoryBeans;
    }

    private CategoryBean getCategoryBean(Category category) {
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setId(category.getId());
        categoryBean.setDescription(category.getDescription());
        categoryBean.setName(category.getName());
        return categoryBean;
    }

    public List<SubCategoryBean> getSubCategoryBeans(Long categoryId) {
        List<SubCategory> subCategories = subCategoryDAO.getByCategoryId(categoryDAO.findByID(categoryId));
        List<SubCategoryBean> subCategoryBeans = new ArrayList<SubCategoryBean>();
        for (SubCategory subCategory : subCategories)
            subCategoryBeans.add(getSubCategoryBean(subCategory));
        return subCategoryBeans;
    }

    private SubCategoryBean getSubCategoryBean(SubCategory subCategory) {
        SubCategoryBean subCategoryBean = new SubCategoryBean();
        subCategoryBean.setId(subCategory.getId());
        subCategoryBean.setDescription(subCategory.getDescription());
        subCategoryBean.setName(subCategory.getName());
        subCategoryBean.setCategoryId(subCategory.getCategory().getId());
        for (String restaurant : restaurantSubCategoryMappingDAO.getRestaurantsNameBySubCategory(subCategory))
            subCategoryBean.getRestaurants().add(restaurant);
        return subCategoryBean;
    }

    public List<ItemBean> getItems(Long subCategoryId) {
        List<Item> items = itemDAO.getItems(subCategoryDAO.findByID(subCategoryId));
        List<ItemBean> itemBeans = new ArrayList<ItemBean>();
        for(Item item:items)
            itemBeans.add(getItemBean(item));
        return itemBeans;
    }

    private ItemBean getItemBean(Item item){
        ItemBean itemBean = new ItemBean();
        itemBean.setId(item.getId());
        itemBean.setName(item.getName());
        itemBean.setDescription(item.getDescription());
        itemBean.setPrice(item.getPrice());
        itemBean.setSubCategoryId(item.getSubCategory().getId());
        return itemBean;
    }
}
