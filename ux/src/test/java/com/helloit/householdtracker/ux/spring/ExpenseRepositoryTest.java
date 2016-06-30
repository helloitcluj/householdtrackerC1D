package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IExpenseRepository;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.tools.SchemaManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Student on 6/29/2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class ExpenseRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IExpenseRepository expenseRepository;
    User testUser;

    @Before
    public void setup() {
       final SchemaManager schemaManager = new SchemaManager();
        schemaManager.recreateSchema();

        final User user = new User();
        user.setUserName("Test");
        user.setPassword("password");

        testUser = userRepository.save(user);
    }

    @Test
    public void basicTest(){
        Calendar now = Calendar.getInstance();
        final Expense expense = new Expense(32.5, now, "Chocolate", testUser.getId());

        Expense saved = expenseRepository.save(expense);
        Assert.assertEquals("Should have id", new Integer(0), saved.getId());

    }


    @Test
    public void ForeignKeyTest(){
        Calendar now = Calendar.getInstance();
        final Expense expense = new Expense(32.5, now, "Chocolate", testUser.getId());

        try {
            Expense saved = expenseRepository.save(expense);
            throw new UnsupportedOperationException("Should not happen");
        } catch (RuntimeException ex){
            //Assert.assertEquals("Should have id", new Integer(0), saved.getId());
        }
    }

}
