package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.Expense;
import java.util.List;

/**
 * Created by Student on 7/18/2016.
 */
public class ExpenseListTO {
    private List<Expense> expense;
    private Integer nrOfExpenses;

    public ExpenseListTO(List<Expense> expense, Integer nrOfExpenses) {
        this.expense = expense;
        this.nrOfExpenses = nrOfExpenses;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }

    public Integer getNrOfExpenses() {
        return nrOfExpenses;
    }

    public void setNrOfExpenses(Integer nrOfExpenses) {
        this.nrOfExpenses = nrOfExpenses;
    }
}
