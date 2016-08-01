package com.helloit.householdtracker.ux.spring;


import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.spring.account.AccountController;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//mandatory - related to a dependency from Gradle:
// testCompile group: "org.springframework", name: "spring-test", version: "$springVersion"
@RunWith(SpringJUnit4ClassRunner.class)

//config classes, mandatory. Can be specifically defined for the Test
@ContextConfiguration(classes = {TestAppConfig.class, WebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Sql({ "/create-schema.sql", "/insert-test-users.sql" })
public class AccountControllerTest {

    //
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(this.wac).build(); //method coming from static import. See above
    }

    //User Registration TESTS.
    //Just testing the controller, how the controller readcted to the request. Not testing the model content
    @Test
    public void registrationSuccessTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "test")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_SUCCESS))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "You are a lucky fellow!"))
        ;
    }

    @Test
    public void registrationMismatchingPasswordTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "123").param("ReType", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Retyped password did not match!"))
        ;
    }

    @Test
    public void registrationExistingAccountTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Account 'aron' already exists!"))
        ;
    }

    @Test
    public void registrationMissingUsernameTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "")
                .param("Pasword", "123").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a username!"))
        ;
    }

    @Test
    public void registrationMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/account/userRegistration")
                .param("Uname", "aron")
                .param("Pasword", "").param("ReType", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.REGISTER_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a password!"))
        ;
    }

    @Test
    public void userRegistrationTest() throws Exception {
        mockMvc.perform(get("/account/userRegistration"))
                .andExpect(status().isOk())
                .andExpect(view().name("userRegistration"))
        ;
    }

    //User Login TESTS
    @Test
    public void test01_loginSuccessTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", "123"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"))
        ;
    }

    @Test
    public void test02_loginInvalidCredentialTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aronka")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Invalid credentials!"))
        ;
    }

    @Test
    public void test03_loginMissingUsernameTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a username!"))
        ;
    }

    @Test
    public void test04_loginMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a password!"))
        ;
    }

    @Test
    public void test05_loginTest() throws Exception {
        mockMvc.perform(get("/account/userLoginController"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
         ;
    }

    //Login AJAX
    @Test
    public void test06_loginAjaxSuccessTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", "123"))

        ;
    }

    @Test
    public void test07_loginAjaxInvalidCredentialTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aronka")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Invalid credentials!"))
        ;
    }

    @Test
    public void test08_loginAjaxMissingUsernameTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "")
                .param("Pasword", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a username!"))
        ;
    }

    @Test
    public void test09_loginAjaxMissingPasswordTest() throws Exception {
        mockMvc.perform(post("/account/userLoginController")
                .param("Uname", "aron")
                .param("Pasword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.LOGIN_ERROR))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter a password!"))
        ;
    }

    //Change Password Navigation TESTS
    @Test
    public void changePasswordNavigationTest() throws Exception {
        mockMvc.perform(get("/account/changePasswordNavigationController"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
        ;
    }

    //Change password  TESTS
    @NotNull
    private HashMap<String, Object> setSessionAttributes(final String testUserName, String testUserPassword) {
        HashMap<String, Object> sessionAttributes = new HashMap<>();

        User loggedInUser = new User();
        loggedInUser.setUserName(testUserName);
        loggedInUser.setPassword(testUserPassword);
        sessionAttributes.put("currentUser", loggedInUser);
        return sessionAttributes;
    }

    @Test
    public void test10_changePasswordInvalidCredentialTest() throws Exception {
        HashMap<String, Object> sessionAttributes = setSessionAttributes("aron", "");

        mockMvc.perform(post("/account/changePasswordController")
                .sessionAttrs(sessionAttributes)
                .param("oldPassword", "1234")
                .param("newPassword", "12345").param("reTypeNewPassword", "12345"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Your current password is not correct. Please check and re-type"))
        ;
    }


    @Test
    public void test11_changePasswordDoNotMachTest() throws Exception {
        HashMap<String, Object> sessionAttributes = setSessionAttributes("aron", "");
        mockMvc.perform(post("/account/changePasswordController")
                .sessionAttrs(sessionAttributes)
                .param("oldPassword", "123")
                .param("newPassword", "12345").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Passwords do not match. Plese re-type!"))
        ;
    }

    @Test
    public void test12_changePasswordMissingPasswordTest() throws Exception {
        HashMap<String, Object> sessionAttributes = setSessionAttributes("aron", "");
        mockMvc.perform(post("/account/changePasswordController")
                .sessionAttrs(sessionAttributes)
                .param("oldPassword", "")
                .param("newPassword", "1234").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter your current password!"))
        ;
    }

    @Test
    public void test13_changePasswordMissingRetypedPasswordTest() throws Exception {
        HashMap<String, Object> sessionAttributes = setSessionAttributes("aron", "");
        mockMvc.perform(post("/account/changePasswordController")
                .sessionAttrs(sessionAttributes)
                .param("oldPassword", "123")
                .param("newPassword", "1234").param("reTypeNewPassword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Please enter your new password!"))
        ;
    }

    @Test
    public void test14_changePasswordSuccessTest() throws Exception {
        HashMap<String, Object> sessionAttributes = setSessionAttributes("aron", "123");

        mockMvc.perform(post("/account/changePasswordController")
                .sessionAttrs(sessionAttributes)
                .param("oldPassword", "123")
                .param("newPassword", "1234").param("reTypeNewPassword", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name(AccountController.PASSWORD_CHANGE))
                .andExpect(model().attribute(AccountController.MESSAGE_TAG, "Your password is changed!"))
        ;
    }


    //User Logout TESTS
/*    @Test
    public void test30_logoutSuccessTest() throws Exception {
        mockMvc.perform(get("/account/logoutController"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"))
        ;
    }*/

}
