package com.helloit.householdtracker.ux.spring.login;

import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.spring.account.AccountController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Student on 5/25/2016.
 */

@Service
@Controller
@RequestMapping("userLoginController")
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @Resource
    private IUserRepository userRepository;


    @RequestMapping(path="userLoginController" ,method = RequestMethod.POST)
    public String userLogin(@RequestParam("Pasword") String pasword, @RequestParam("Uname") String uname, final ModelMap model) {
        String resultPage = "registerInput";
        String messageParameter = "message";
        String resultMessage = "Invalid credentials!";



        if (pasword != null && !pasword.equals("")) {
            if (uname != null && !uname.equals("")) {
                List<User> users = userRepository.findByUsernameAndPassword(uname, pasword);
                if (users.size() == 0) {
                    resultPage = "userLogin";
                } else {
                    resultMessage = "You are in!";
                }
            } else {
                resultPage = "userLogin";
            }
        } else {
            resultPage = "userLogin";
        }

        model.addAttribute(messageParameter, resultMessage);

        return resultPage;
    }
}
