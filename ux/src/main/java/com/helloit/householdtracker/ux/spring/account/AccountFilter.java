package com.helloit.householdtracker.ux.spring.account;


import com.helloit.householdtracker.ux.common.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Student on 6/23/2016.
 */
public class AccountFilter implements Filter {

    public void destroy(){
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse)resp;

        String requestURI = httpServletRequest.getRequestURI();

        if(requestURI.contains("/account/")){
            chain.doFilter(req, resp);
        } else {
            HttpSession httpSession = httpServletRequest.getSession(false);
            if(httpSession != null){
                User currentUser = (User) httpSession.getAttribute("currentUser");
                if (currentUser != null){
                    chain.doFilter(req, resp);
                } else {
                    httpServletResponse.sendRedirect("account/userLoginController");
                }

            } else {
                    httpServletResponse.sendRedirect("account/userLoginController");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }


}
