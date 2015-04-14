package com.ase.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Suresh
 * Date: 4/17/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */

@MappedSuperclass
public abstract class Base implements Serializable {

    //    private static final long serialVersionUID = 3662257149191994534L;
    @Id
    @GeneratedValue
    private Long id;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Version
    private Integer version;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime = new Date();
     @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();
    private String lastUpdatedBy;
    private String comments;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


}
