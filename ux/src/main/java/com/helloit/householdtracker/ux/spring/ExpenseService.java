package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Student on 6/29/2016.
 */

@Service
public class ExpenseService implements IExpenseService {

    private final IExpenseRepository expenseRepository;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Autowired
    public ExpenseService(IExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense save (String date, final double amount, final String description, final Integer userId) {

        final Expense expense = new Expense(amount, convert(date), description, userId);

        return expenseRepository.save(expense);
    }


    public List<Expense> getExpenseList(String pageNr) {

        int pageNrInt = Integer.parseInt(pageNr);

        Page<Expense> expensePage = expenseRepository.findAll(new PageRequest(pageNrInt - 1,5));

        return expensePage.getContent();
    }

    public Integer getNrOfExpenses(){
        Integer expenseNr = 0;
        List<Expense> expenseList = expenseRepository.findAll();
        expenseNr = expenseList.size();
        return expenseNr;
    }

    private Calendar convert(String dateAsString) {

        Calendar result = null;

        try {
            Date date = formatter.parse(dateAsString);
            result = Calendar.getInstance();
            result.setTime(date);
        } catch (final ParseException ignored) {
            //ignored
        }

        return result;
    }
}
