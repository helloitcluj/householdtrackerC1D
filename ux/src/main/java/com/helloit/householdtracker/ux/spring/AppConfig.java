package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.IAccountService;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.spring.account.AccountService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration //mandatory
@ComponentScan //

//config can be split into smaller units. These can be imported, because only one main can be added in web.xml
@Import(PersistenceConfig.class)

//spring specific reference to java specific config files. data from this file can be used in config classes, e.g. PersistenceConfig
//goal: make certain properties flexible for later change or flexible use - these values can be read with @Value annotation.
//app.properties file name is custom. Will look always in 'resources' directory. Subfolders can be added as well
@PropertySource("classpath:app.properties")
public class AppConfig {

    //this bean is needed to be able to use the above @PropertySource correctly
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public IAccountService accountService(final IUserRepository userRepository) {
        return new AccountService(userRepository);
    }

}