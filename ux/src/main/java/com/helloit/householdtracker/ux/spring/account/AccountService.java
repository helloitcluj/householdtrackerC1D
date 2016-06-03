package com.helloit.householdtracker.ux.spring.account;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
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

}
