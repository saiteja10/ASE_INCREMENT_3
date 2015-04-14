package com.ase.api.service.rest.implementation;

import com.ase.api.aop.MyTransaction;
import com.ase.api.service.CatalogService;
import com.ase.bean.list.CategoryBeanList;
import com.ase.bean.list.ItemBeanList;
import com.ase.bean.list.SubCategoryBeanList;
import com.ase.handler.ItemHandler;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by Gurrala on 3/16/2015.
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    @Inject
    ItemHandler itemHandler;

    @MyTransaction
    @Override
    public CategoryBeanList getAllCategories() {
        CategoryBeanList categoryBeanList = new CategoryBeanList();
        categoryBeanList.setCategoryBeans(itemHandler.getAllCategories());
        return categoryBeanList;
    }

    @MyTransaction
    @Override
    public SubCategoryBeanList getSubCategories(Long categoryId) {
        SubCategoryBeanList subCategoryBeanList = new SubCategoryBeanList();
        subCategoryBeanList.setSubCategoryBeans(itemHandler.getSubCategoryBeans(categoryId));
        return subCategoryBeanList;
    }

    @MyTransaction
    @Override
    public ItemBeanList getItems(Long subCategoryId) {
        ItemBeanList itemBeanList = new ItemBeanList();
        itemBeanList.setItemBeans(itemHandler.getItems(subCategoryId));
        return itemBeanList;
    }
}
