package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Student on 5/21/2016.
 */
public interface IUserRepository extends JpaRepository<User, Integer> {
}
