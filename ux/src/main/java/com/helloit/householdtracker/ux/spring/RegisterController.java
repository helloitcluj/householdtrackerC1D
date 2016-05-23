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


@Service
@Controller
@RequestMapping("registerRequest")
public class RegisterController {

    public static final String MESSAGE_PARAMETER_TAG = "message";
    public static final String HELLO_VIEW_TAG = "registerInput";
    public static final String SAMPLE_TEXT = "Hello world!";
    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
    //@Resource
    private IUserRepository userRepository;

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String printWelcome(final ModelMap model) {
        LOGGER.info("welcome!");

        final User entity = new User();
        entity.setUsername("test");
//        entity.setEmplNumber(1);
        final User savedEntity = userRepository.save(entity);

        model.addAttribute(MESSAGE_PARAMETER_TAG, SAMPLE_TEXT);
        return HELLO_VIEW_TAG;
    }
}