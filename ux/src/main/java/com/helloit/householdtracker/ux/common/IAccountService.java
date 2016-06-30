package com.helloit.householdtracker.ux.common;


import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.spring.account.UserTransfer;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Student on 5/30/2016.
 */
public interface IAccountService {


    CreationOutcomes registerAccount(@NotNull String userName, @NotNull String password, @NotNull String retypedPassword);

    CreationOutcomes loginAccount(@NotNull String userName, @NotNull String password);

    User getLoggedInUser(String uname, String pasword);

    CreationOutcomes changeAccountPassword(User user, @NotNull String oldPassword, @NotNull String newPassword, @NotNull String reTypeNewPassword);

    List<User> getUserList();

    User getUser(Integer id);

    UserTransfer getUserTransfer(User user);

    void saveUserDetails(User user);

    enum CreationOutcomes {
        SUCCESS,
        RETYPED_PASSWORD_DO_NOT_MATCH,
        MISSING_USERNAME,
        MISSING_PASSWORD,
        INVALID_CREDENTIAL,
        MISSING_NEW_PASSWORD,
        EXISTING_ACCOUNT
    }

}
