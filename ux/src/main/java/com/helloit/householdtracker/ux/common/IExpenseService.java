package com.helloit.householdtracker.ux.common;

import com.helloit.householdtracker.ux.common.entities.Expense;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Student on 6/29/2016.
 */
public interface IExpenseService {
    Expense save(Calendar date, double amount, String description, Integer userId);
}
