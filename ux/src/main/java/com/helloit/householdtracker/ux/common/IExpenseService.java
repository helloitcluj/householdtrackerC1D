package com.helloit.householdtracker.ux.common;

import com.helloit.householdtracker.ux.common.entities.Expense;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Student on 6/29/2016.
 */
public interface IExpenseService {

    Expense save(String date, double amount, String description, Integer userId);

    List<Expense> getExpenseList(String pageNr);

    Integer getNrOfExpenses();
}
