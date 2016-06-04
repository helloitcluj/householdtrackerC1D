package com.helloit.householdtracker.ux.spring.account;

import com.helloit.householdtracker.ux.common.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Service
@Controller
public class AccountController {

    public static final String MESSAGE_TAG = "message";
    public static final String REGISTER_SUCCESS = "userRegistration";
    public static final String REGISTER_ERROR = "userRegistration";
    public static final String LOGIN_SUCCESS = "homepage";
    public static final String LOGIN_ERROR = "userLogin";
    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @Autowired
    private IAccountService registerService;


    @Transactional
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

    @Transactional
    @RequestMapping(path="userLoginController" ,method = RequestMethod.POST)
    public String userLogin(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, final ModelMap model, HttpSession session) {

        final IAccountService.CreationOutcomes outcome = registerService.loginAccount(uname, pasword);


        final String resultPage;

        switch (outcome) {
            case SUCCESS: {
                String currentUserName = "";
                resultPage = LOGIN_SUCCESS;
                model.addAttribute(MESSAGE_TAG, "You are a lucky fellow"+uname+"!");
                session.setAttribute("currentUser",uname);
                currentUserName = (String)session.getAttribute("currentUser");
                model.addAttribute("loggedInUser", currentUserName);
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

    @Transactional
    @RequestMapping(path="logoutController" ,method = RequestMethod.GET)
    public String userLogout(HttpSession session) {

        final String resultPage="redirect:/home";
        session.invalidate();

        return resultPage;
    }

}