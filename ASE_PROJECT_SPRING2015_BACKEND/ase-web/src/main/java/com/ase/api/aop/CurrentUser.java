package com.ase.api.aop;


/**
 * Created with IntelliJ IDEA.
 * User: Vivek
 * Date: 11/27/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentUser {
    private Long id;
    private boolean myfashionUser;
    private String apiKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMyfashionUser() {
        return myfashionUser;
    }

    public void setMyfashionUserType(boolean myfashionUser) {
        this.myfashionUser = myfashionUser;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
