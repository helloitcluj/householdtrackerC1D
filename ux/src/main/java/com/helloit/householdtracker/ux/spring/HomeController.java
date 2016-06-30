package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Student on 5/25/2016.
 */
@Service
@Controller
public class HomeController {

    @RequestMapping(path = "/")
    public String homeRedirect(HttpSession session, ModelMap model) {
        User loggedInUser = (User) session.getAttribute("currentUser");
        if(loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser.getUserName());
        }
        return "homepage";
    }
}
