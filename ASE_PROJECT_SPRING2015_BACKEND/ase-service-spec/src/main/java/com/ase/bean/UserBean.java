package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gurrala on 2/25/2015.
 */
@XmlRootElement(namespace = "ase")
public class UserBean extends BaseBean {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
