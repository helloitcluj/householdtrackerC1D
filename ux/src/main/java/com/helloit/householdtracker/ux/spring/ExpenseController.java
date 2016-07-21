package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Student on 6/29/2016.
 */

@Controller

@RequestMapping(path="expense")
public class ExpenseController {

    @Autowired
    IExpenseService expenseService;

    @Autowired
    IAccountService accountService;

    @RequestMapping(path="create" ,method = RequestMethod.POST)
    @ResponseBody
    public String create(final HttpSession session, final String date, final double amount, final String description) {

        Integer userId = getCurrentUser(session);

        expenseService.save(date, amount, description, userId);

        return "Success";
    }

    private Integer getCurrentUser(HttpSession session) {
        Integer userId = null;
        String userName = (String) session.getAttribute("currentUser");
        User currentUser = (User)session.getAttribute("currentUser");  //accountService.find(userName);
        userId = currentUser.getId();
        return userId;
    }

    @RequestMapping(path = "{expenseId}", method = RequestMethod.GET)
    public Expense getById(@PathVariable final Integer expenseId, final HttpSession session) {
        Integer userId = getCurrentUser(session);
        return expenseService.getByIdAndUserId(expenseId, userId);
    }

}
