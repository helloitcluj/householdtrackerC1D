package com.helloit.householdtracker.ux.spring;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Student on 5/25/2016.
 */
@Service
@Controller
public class HomeController {

    @RequestMapping(path = "home")
    public String homeRedirect() {
        return "userLogin";
    }

    @RequestMapping(path = "redirect", method = RequestMethod.GET)
    public String redirect() {
        return "userRegistration";
    }
}
