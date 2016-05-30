package com.helloit.householdtracker.ux.spring.register;

import com.helloit.householdtracker.ux.common.IRegisterService;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
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

import javax.annotation.Resource;
import java.util.List;


@Service
@Controller
@RequestMapping("userRegistration")
public class RegisterController {

    public static final String MESSAGE_TAG = "message";
    public static final String ACCOUNT_SUCCESS = "registerInput";
    public static final String ACCOUNT_ERROR = "userRegistration";
    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    @Autowired
    private IRegisterService registerService;

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String userRegistration(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, @RequestParam("ReType") String retype, final ModelMap model) {

        LOGGER.info("Creating account!");

        final IRegisterService.CreationOutcomes outcome = registerService.registerAccount(uname, pasword, retype);


        String resultPage;

        switch (outcome) {
            case SUCCESS: {
                resultPage = ACCOUNT_SUCCESS;
                break;
            }
            case RETYPED_PASSWORD_DO_NOT_MATCH: {
                resultPage = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_TAG, "Retyped password did not match!");
                break;
            }
            case EXISTING_ACCOUNT: {
                resultPage = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_TAG, "Account '" + uname + "' already exists!");
                break;
            }
            case MISSING_USERNAME: {
                resultPage = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a username!");
                break;
            }
            case MISSING_PASSWORD: {
                resultPage = ACCOUNT_ERROR;
                model.addAttribute(MESSAGE_TAG, "Please enter a password!");
                break;
            }
            default: {
                throw new UnsupportedOperationException("Not supported case!");
            }
        }

        return resultPage;
    }

}