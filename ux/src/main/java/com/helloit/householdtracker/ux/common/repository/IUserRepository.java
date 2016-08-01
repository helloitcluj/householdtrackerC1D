package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Student on 5/21/2016.
 */


public interface IUserRepository extends JpaRepository<User, Integer> {

    public List<User> findByUsername(String username);

    public List<User> findByUsernameAndPassword(String username, String password);

    public User findOneById(Integer id);
}
