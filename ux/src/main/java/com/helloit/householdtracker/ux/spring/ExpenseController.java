package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(path="create" ,method = RequestMethod.POST)
    @ResponseBody
    public String create(final HttpSession session, final Calendar date, final double amount, final String description) {

        Integer userId = null;
        User currentUser = (User)session.getAttribute("currentUser");
        userId = currentUser.getId();

        expenseService.save(date, amount, description, userId);

        return "Success";
    }

}
