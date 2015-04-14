package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gurrala on 2/21/2015.
 */
@XmlRootElement(namespace = "ase")
public class BaseBean extends SuperDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
