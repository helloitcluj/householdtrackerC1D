package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Student on 6/29/2016.
 */
@Service
public class ExpenseService implements IExpenseService {

    private final IExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(IExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense save(final Calendar date,final double amount,final String description,final Integer userId) {

        Expense expense = new Expense(amount, date, description, userId);

        Expense savedExpense = expenseRepository.save(expense);

        return savedExpense;
    }
}
