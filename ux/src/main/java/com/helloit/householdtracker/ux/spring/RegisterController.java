package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public static final String MESSAGE_PARAMETER_TAG = "message";
    public static final String REGISTER_VIEW_TAG = "userRegistration";
    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    @Resource
    private IUserRepository userRepository;

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String userRegistration(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, @RequestParam("ReType") String retype, final ModelMap model) {

        LOGGER.info("Start!");

        String resultPage = REGISTER_VIEW_TAG;
        String resultMessage;

        if (pasword != null && !pasword.equals("")) {
            if (pasword.equals(retype)) {
                if (uname != null && !uname.equals("")) {
                    List<User> users = userRepository.findByUsername(uname);
                    if (users.size() == 0) {
                        final User entity = new User();
                        entity.setUserName(uname);
                        entity.setPassword(pasword);
                        final User savedEntity = userRepository.save(entity);
                        resultMessage = "You have registered successfully";
                        resultPage = "registerInput";
                    } else {
                        resultMessage = "Your username is already taken";
                    }
                } else {
                    resultMessage = "Please enter a username";
                }
            } else {
                resultMessage = "Your passwords do not match! Please retype your password";
            }
        } else {
            resultMessage = "Please enter a password";
        }
        model.addAttribute(MESSAGE_PARAMETER_TAG, resultMessage);
        return resultPage;
    }

}