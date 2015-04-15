package com.ase.api.service;

import com.ase.bean.list.CategoryBeanList;
import com.ase.bean.list.ItemBeanList;
import com.ase.bean.list.SubCategoryBeanList;
import org.apache.cxf.jaxrs.model.wadl.ElementClass;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by sai on 3/16/2015.
 */
@Service
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface CatalogService {
    @Path("/category")
    @GET
    @ElementClass(response = CategoryBeanList.class)
    public CategoryBeanList getAllCategories();

    @Path("/subcategory/{categoryId}")
    @GET
    @ElementClass(response = SubCategoryBeanList.class)
    public SubCategoryBeanList getSubCategories(@PathParam("categoryId") Long categoryId);

    @Path("/item/{subCategoryId}")
    @GET
    @ElementClass(response = ItemBeanList.class)
    public ItemBeanList getItems(@PathParam("subCategoryId") Long subCategoryId);
}
