package com.helloit.householdtracker.ux.common.repository;

import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Student on 6/29/2016.
 */
public interface IExpenseRepository  extends JpaRepository<Expense, Integer> {

}
