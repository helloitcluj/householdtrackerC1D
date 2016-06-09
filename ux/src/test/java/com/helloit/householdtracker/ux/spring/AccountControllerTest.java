package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.spring.AppConfig;
import com.helloit.householdtracker.ux.spring.WebConfig;
import com.helloit.householdtracker.ux.spring.account.AccountController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class AccountControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    //User Registration TESTS
    @Test
    public void registrationSuccessTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_SUCCESS))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "You are a lucky fellow!"))
        ;
    }

    @Test
    public void registrationMismatchingPasswordTest() throws Exception {
        mockMvc.perform(post("/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "123").param("ReType", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Retyped password did not match!"))
        ;
    }

    @Test
    public void registrationExistingAccountTest() throws Exception {
        mockMvc.perform(post("/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Account 'aron' already exists!"))
        ;
    }

    @Test
    public void registrationMissingUsernameTest() throws Exception {
        mockMvc.perform(post("/userRegistration")
                .param("Uname", "")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a username!"))
        ;
    }

    @Test
    public void registrationMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a password!"))
        ;
    }

    //User Login TESTS
    @Test
    public void loginSuccessTest() throws Exception {
        mockMvc.perform(post("/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_SUCCESS))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "You are a lucky fellow aron!"))
        ;
    }

    @Test
    public void loginInvalidCredentialTest() throws Exception {
        mockMvc.perform(post("/userLoginController")
                .param("Uname", "aronka")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Invalid credentials!"))
        ;
    }

    @Test
    public void loginMissingUsernameTest() throws Exception {
        mockMvc.perform(post("/userLoginController")
                .param("Uname", "")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a username!"))
        ;
    }

    @Test
    public void loginMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a password!"))
        ;
    }

    //User Logout TESTS
    @Test
    public void logoutSuccessTest() throws Exception {
        mockMvc.perform(post("/logoutController"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REDIRECT_TO_HOME))
        ;
    }

    //Change Password Navigation TESTS
    @Test
    public void changePasswordNavigatioTest() throws Exception {
        mockMvc.perform(post("/changePasswordNavigationController"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.CHANGE_PASSWORD))
        ;
    }

    //Change password  TESTS
    @Test
    public void changePasswordInvalidCredentialTest() throws Exception {
        mockMvc.perform(post("/changePasswordController")
                .param("oldPassword", "123")
                .param("newPassword", "1234").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Your current password is not correct. Please check and re-type"))
        ;
    }

    @Test
    public void changePasswordDoNotMachTest() throws Exception {
        mockMvc.perform(post("/changePasswordController")
                .param("oldPassword", "123")
                .param("Pasword", "1234").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Passwords do not match. Plese re-type!"))
        ;
    }

    @Test
    public void changePasswordMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/changePasswordController")
        .param("oldPassword", "")
        .param("newPassword", "1234").param("reTypeNewPassword", "1234"))
        .andExpect(status().isOk())
        .andExpect(view().name(AccountController.PASSWORD_CHANGE))
        .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter your current password!"))
        ;
    }

    @Test
    public void changePasswordMissingRetypedPasswordTest() throws Exception {
        mockMvc.perform(post("/changePasswordController")
                .param("oldPassword", "123")
                .param("newPassword", "1234").param("reTypeNewPassword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter your new password!"))
        ;
    }

    @Test
    public void changePasswordSuccessTest() throws Exception {
        mockMvc.perform(post("/changePasswordController")
                .param("oldPassword", "123")
                .param("newPassword", "1234").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Your password is changed!"))
        ;
    }
}
