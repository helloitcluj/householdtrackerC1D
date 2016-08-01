package com.helloit.householdtracker.ux.common.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

/**
 * Created by Student on 5/21/2016.
 */

//mandatory
@Entity

//map to db table if the entity name is not the same
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //used if the names are not identical
    @Column(name = "username", unique = true)
    private String username;

    private String password;

    private Boolean disabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
