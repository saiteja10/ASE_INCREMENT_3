package com.ase.bean.list;

import com.ase.bean.SubCategoryBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Gurrala on 3/16/2015.
 */
@XmlRootElement(namespace = "ase")
public class SubCategoryBeanList {
    private List<SubCategoryBean> subCategoryBeans;

    @XmlElement
    public List<SubCategoryBean> getSubCategoryBeans() {
        return subCategoryBeans;
    }

    public void setSubCategoryBeans(List<SubCategoryBean> subCategoryBeans) {
        this.subCategoryBeans = subCategoryBeans;
    }
}
