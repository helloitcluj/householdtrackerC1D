package com.helloit.householdtracker.ux.common.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Student on 6/29/2016.
 */

@Entity
@Table(name="expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    private String description;
    private Integer userId;


    public Expense(final double amount, final Calendar date, final String description, final Integer userId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

    public Expense(){
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountId() {
        return userId;
    }

    public void setAccountId(Integer accountId) {
        this.userId = accountId;
    }

}



