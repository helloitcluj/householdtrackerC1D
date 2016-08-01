package com.helloit.householdtracker.ux.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc //if missing, will not take into consideration the parent class's overridden methods - in this case wont return static resources.
@ComponentScan //needed to be able to find the correct controller based on url. This will look for components (@Controller-s, @Service-s) only in the same package
                //another packages can be added as prameter, e.g. ("aron.sinoai.springmvcjpa.spring.test")
                //multiple prameters can be added, e.g. ({"aron.sinoai.springmvcjpa.spring.test", "com.test.test"})

//if content is missing 404 error will be returned on all requests
public class WebConfig extends WebMvcConfigurerAdapter {

    //this will enable the listed static resources to be returned.
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        final ResourceHandlerRegistration jsRegistration = registry.addResourceHandler("/js/**");
        jsRegistration.addResourceLocations("/js/");
        final ResourceHandlerRegistration cssRegistration = registry.addResourceHandler("/css/**");
        cssRegistration.addResourceLocations("/css/");
        final ResourceHandlerRegistration imagesRegistration = registry.addResourceHandler("/images/**");
        imagesRegistration.addResourceLocations("/images/");
        final ResourceHandlerRegistration homePageRegistration = registry.addResourceHandler("/*.html");
        homePageRegistration.addResourceLocations("/");
    }


    @Bean
    public InternalResourceViewResolver resolver() {
        final InternalResourceViewResolver result = new InternalResourceViewResolver();
        result.setPrefix("/WEB-INF/pages/");
        result.setSuffix(".jsp");

        return result;
    }


}