package com.ase.bean.list;

import com.ase.bean.CategoryBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@XmlRootElement(namespace = "ase")
public class CategoryBeanList {
    private List<CategoryBean> categoryBeans;

    @XmlElement
    public List<CategoryBean> getCategoryBeans() {
        return categoryBeans;
    }

    public void setCategoryBeans(List<CategoryBean> categoryBeans) {
        this.categoryBeans = categoryBeans;
    }
}
