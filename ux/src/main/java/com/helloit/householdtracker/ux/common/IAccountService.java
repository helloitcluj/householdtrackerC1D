package com.helloit.householdtracker.ux.common;


import org.jetbrains.annotations.NotNull;

/**
 * Created by Student on 5/30/2016.
 */
public interface IAccountService {


    CreationOutcomes registerAccount(@NotNull String userName, @NotNull String password, @NotNull String retypedPassword);

    CreationOutcomes loginAccount(@NotNull String userName, @NotNull String password);

    enum CreationOutcomes {
        SUCCESS,
        RETYPED_PASSWORD_DO_NOT_MATCH,
        MISSING_USERNAME,
        MISSING_PASSWORD,
        INVALID_CREDENTIAL,
        EXISTING_ACCOUNT
    }

}
