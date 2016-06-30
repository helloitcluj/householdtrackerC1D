package com.helloit.householdtracker.ux.spring.account;

import com.helloit.householdtracker.ux.common.entities.User;

/**
 * Created by Student on 6/27/2016.
 */
public class UserTransfer {

    public UserTransfer(){

    }

    public UserTransfer(User user){
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.disabledUser = user.getDisabled();
        this.id = user.getId();
    }

    private Integer id;
    private String username;
    private String password;
    private Boolean disabledUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisabled() {
        return disabledUser;
    }

    public void setDisabled(Boolean disabled) {
        this.disabledUser = disabled;
    }
}
