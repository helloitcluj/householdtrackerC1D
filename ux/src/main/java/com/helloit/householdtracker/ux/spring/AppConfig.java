package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IRegisterService;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.spring.register.RegisterService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@Import(PersistenceConfig.class)
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public IRegisterService accountService(final IUserRepository userRepository) {
        return new RegisterService(userRepository);
    }

}