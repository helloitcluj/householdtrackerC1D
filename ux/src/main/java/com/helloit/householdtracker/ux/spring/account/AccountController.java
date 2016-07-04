package com.helloit.householdtracker.ux.spring.account;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.IExpenseService;
import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

@RequestMapping(path="account")

public class AccountController {

    public static final String MESSAGE_TAG = "message";
    public static final String REGISTER_SUCCESS = "userRegistration";
    public static final String REGISTER_ERROR = "userRegistration";
    public static final String LOGIN_ERROR = "userLogin";
    public static final String PASSWORD_CHANGE = "changePassword";
    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @Autowired
    private IAccountService registerService;

    @Autowired
    private IExpenseService expenseService;

    @RequestMapping(path = "userRegistration", method = RequestMethod.GET)
    public String userRegistration() {
        return "userRegistration";
    }

    @RequestMapping(path="userRegistration", method = RequestMethod.POST)
    public String userRegistration(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname,
                                   @RequestParam("ReType") String retype, final ModelMap model) {

        LOGGER.info("Creating account!");

        final IAccountService.CreationOutcomes outcome = registerService.registerAccount(uname, pasword, retype);


        final String resultPage;

        switch (outcome) {
            case SUCCESS: {
                resultPage = REGISTER_SUCCESS;
                model.addAttribute(MESSAGE_TAG, "You are a lucky fellow!");
                model.addAttribute("displayMetod", "display: none");
                break;
            }
            case RETYPED_PASSWORD_DO_NOT_MATCH: {
                resultPage = REGISTER_ERROR;
                model.addAttribute(MESSAGE_TAG, "Retyped password did not match!");
                break;
            }
            case EXISTING_ACCOUNT: {
                resultPage = REGISTER_ERROR;
                model.addAttribute(MESSAGE_TAG, "Account '" + uname + "' already exists!");
                break;
            }
            case MISSING_USERNAME: {
                resultPage = REGISTER_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a username!");
                break;
            }
            case MISSING_PASSWORD: {
                resultPage = REGISTER_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a password!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return resultPage;
    }

    @RequestMapping(path="userLoginController" ,method = RequestMethod.GET)
    public String userLogin() {
        return LOGIN_ERROR;
    }

    @RequestMapping(path="userLoginController" ,method = RequestMethod.POST)
    public String userLogin(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, final ModelMap model, HttpSession session) {

        final IAccountService.CreationOutcomes outcome = registerService.loginAccount(uname, pasword);


        final String resultPage;

        switch (outcome) {
            case SUCCESS: {
                String currentUserName = "";
                resultPage = "redirect:/";
                final User loggedInUser = registerService.getLoggedInUser(uname, pasword);
                session.setAttribute("currentUser",loggedInUser);
                break;
            }
            case INVALID_CREDENTIAL: {
                resultPage = LOGIN_ERROR;
                model.addAttribute(MESSAGE_TAG, "Invalid credentials!");
                break;
            }
            case MISSING_USERNAME: {
                resultPage = LOGIN_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a username!");
                break;
            }
            case MISSING_PASSWORD: {
                resultPage = LOGIN_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a password!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return resultPage;
    }

    @RequestMapping(path="loginAjax" ,method = RequestMethod.POST)
    public @ResponseBody
    AccountResult loginAjax(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, final ModelMap model, HttpSession session) {

        final IAccountService.CreationOutcomes outcome = registerService.loginAccount(uname, pasword);


        final AccountResult result;

        switch (outcome) {
            case SUCCESS: {
                result = new AccountResult(AccountResult.Kind.SUCCESS);

                final User loggedInUser = registerService.getLoggedInUser(uname, pasword);
                session.setAttribute("currentUser",loggedInUser);
                break;
            }
            case INVALID_CREDENTIAL: {
                result = new AccountResult(AccountResult.Kind.ERROR, "Invalid credentials!");
                break;
            }
            case MISSING_USERNAME: {
                result = new AccountResult(AccountResult.Kind.ERROR, "Please enter a username!");
                break;
            }
            case MISSING_PASSWORD: {
                result = new AccountResult(AccountResult.Kind.ERROR, "Please enter a password!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return result;
    }

    @RequestMapping(path="logoutController" ,method = RequestMethod.GET)
    public @ResponseBody void userLogout(HttpSession session) {

        final String resultPage="redirect:/index.html";
        session.invalidate();

    }

    @RequestMapping(path="changePasswordNavigationController" ,method = RequestMethod.GET)
    public String changePasswordNavigation(ModelMap model) {

        return "changePassword";
    }

    @RequestMapping(path="changePasswordController" ,method = RequestMethod.POST)
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("reTypeNewPassword") String reTypeNewPassword, final ModelMap model, HttpSession httpSession) {

    User loggedInUser = (User) httpSession.getAttribute("currentUser");

    IAccountService.CreationOutcomes outcome = registerService.changeAccountPassword(loggedInUser, oldPassword, newPassword, reTypeNewPassword);
    final String resultPage;

        switch (outcome){
            case INVALID_CREDENTIAL: {
                resultPage = PASSWORD_CHANGE;
                model.addAttribute(MESSAGE_TAG, "Your current password is not correct. Please check and re-type");
                break;
            }
            case RETYPED_PASSWORD_DO_NOT_MATCH: {
                resultPage = PASSWORD_CHANGE;
                model.addAttribute(MESSAGE_TAG, "Passwords do not match. Plese re-type!");
                break;
            }
            case MISSING_PASSWORD: {
                resultPage = PASSWORD_CHANGE;
                model.addAttribute(MESSAGE_TAG, "Please enter your current password!");
                break;
            }
            case MISSING_NEW_PASSWORD: {
                resultPage = PASSWORD_CHANGE;
                model.addAttribute(MESSAGE_TAG, "Please enter your new password!");
                break;
            }
            case SUCCESS: {
                resultPage = PASSWORD_CHANGE;
                model.addAttribute(MESSAGE_TAG, "Your password is changed!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

    return resultPage;
    }

    @RequestMapping(path="testAjax" ,method = RequestMethod.POST)
    @ResponseBody public List<User> testAjax(HttpSession session) {

        List<User> users = registerService.getUserList();

        return users;
    }

    @RequestMapping(path="expensesAjax" ,method = RequestMethod.POST)
    @ResponseBody public List<Expense> expensesAjax() {

        List<Expense> expenses = expenseService.getExpenseList();

        return expenses;
    }


    @RequestMapping(path="getUserProfile" ,method = RequestMethod.POST)
    @ResponseBody public UserTransfer getUser(Integer id) {

        User user = registerService.getUser(id);
        UserTransfer userTransfer = new UserTransfer(user);

        return userTransfer;
    }

    @RequestMapping(path="registerAjax", method = RequestMethod.POST)
    @ResponseBody String registerAjax(final String pasword, final String uname,
                                   final String retype) {

        LOGGER.info("Creating account!");

        final IAccountService.CreationOutcomes outcome = registerService.registerAccount(uname, pasword, retype);


        final String resultPage;

        switch (outcome) {
            case SUCCESS: {
                resultPage = "You have registered successfully";
                break;
            }
            case RETYPED_PASSWORD_DO_NOT_MATCH: {
                resultPage = "Retyped password did not match!";
                break;
            }
            case EXISTING_ACCOUNT: {
                resultPage = "Account '" + uname + "' already exists!";
                 break;
            }
            case MISSING_USERNAME: {
                resultPage = "Please enter a username!";
                break;
            }
            case MISSING_PASSWORD: {
                resultPage = "Please enter a password!";
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return resultPage;
    }

    @RequestMapping(path="saveUserDetails" ,method = RequestMethod.POST)
    @ResponseBody public String saveUserDetails(@RequestBody UserTransfer userTransfer) {

        User user = registerService.getUser(userTransfer.getId());
        user.setDisabled(userTransfer.getDisabled());

        registerService.saveUserDetails(user);

        return "Success";
    }

}