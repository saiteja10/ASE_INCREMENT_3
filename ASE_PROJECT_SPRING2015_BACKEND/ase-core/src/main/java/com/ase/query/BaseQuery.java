package com.ase.query;

/**
 * 12/4/11 1:41 PM
 *
 * @author siddhartha
 */
public class BaseQuery {
    protected Integer start;
    protected Integer limit;
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
