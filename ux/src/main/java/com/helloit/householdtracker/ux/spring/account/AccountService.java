package com.helloit.householdtracker.ux.spring.account;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 5/30/2016.
 */


@Service
public class AccountService implements IAccountService {

    @Resource
    private IUserRepository userRepository;

    public AccountService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreationOutcomes registerAccount(@NotNull String uname, @NotNull String pasword, @NotNull String retype) {

        CreationOutcomes result;

        if (pasword != null && !pasword.equals("")) {
            if (pasword.equals(retype)) {
                if (uname != null && !uname.equals("")) {
                    List<User> users = userRepository.findByUsername(uname);
                    if (users.size() == 0) {
                        final User entity = new User();
                        entity.setUserName(uname);
                        entity.setPassword(pasword);
                        final User savedEntity = userRepository.save(entity);
                        result = CreationOutcomes.SUCCESS;
                    } else {
                        result = CreationOutcomes.EXISTING_ACCOUNT ;
                    }
                } else {
                    result = CreationOutcomes.MISSING_USERNAME;
                }
            } else {
                result = CreationOutcomes.RETYPED_PASSWORD_DO_NOT_MATCH;
            }
        } else {
            result = CreationOutcomes.MISSING_PASSWORD;
        }

        return result;
    }

    @Override
    public CreationOutcomes loginAccount(@NotNull String uname, @NotNull String pasword){

        CreationOutcomes result;

        if (pasword != null && !pasword.equals("")) {
            if (uname != null && !uname.equals("")) {
                List<User> users = userRepository.findByUsernameAndPassword(uname, pasword);
                if (users.size() == 0) {
                    result = CreationOutcomes.INVALID_CREDENTIAL;
                } else {
                    result = CreationOutcomes.SUCCESS;
                }
            } else {
                result = CreationOutcomes.MISSING_USERNAME;
            }
        } else {
            result = CreationOutcomes.MISSING_PASSWORD;
        }

        return result;
    }

    public User getLoggedInUser(String uname, String pasword){

        List<User> loggegInUserList = new ArrayList<User>();
        User loggedInUser = new User();

        loggegInUserList = userRepository.findByUsernameAndPassword(uname, pasword);

        for (User user : loggegInUserList){
            loggedInUser = user;
        }

        return loggedInUser;
    }

    public CreationOutcomes changeAccountPassword(User user, @NotNull String oldPassword, @NotNull String newPassword, @NotNull String reTypeNewPassword ){
    CreationOutcomes result;

    if(oldPassword.equals("")){
        result = CreationOutcomes.MISSING_PASSWORD;
    } else if(!oldPassword.equals(user.getPassword())) {
        result = CreationOutcomes.INVALID_CREDENTIAL;
    } else if(newPassword.equals("") && reTypeNewPassword.equals("")){
        result = CreationOutcomes.MISSING_NEW_PASSWORD;
    } else if(!newPassword.equals(reTypeNewPassword)){
        result = CreationOutcomes.RETYPED_PASSWORD_DO_NOT_MATCH;
    } else {
        user.setPassword(newPassword);
        final User savedEntity = userRepository.save(user);
        result = CreationOutcomes.SUCCESS;
    }

    return result;
    }

    public List<User> getUserList(){
        List<User> users = userRepository.findAll();
        return users;
    }

}
