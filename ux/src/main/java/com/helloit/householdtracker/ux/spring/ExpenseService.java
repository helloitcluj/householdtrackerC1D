package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Expense> getExpenseList() {

        List<Expense> expense = expenseRepository.findAll();
        //valami
        return expense;

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

    public Expense getByIdAndUserId(Integer expenseId, Integer accountId){
       Expense result = expenseRepository.getOne(expenseId);

        return result == null && result.getAccountId() != accountId ? null : result;

    }
}
