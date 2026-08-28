package com.taskjam.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.Filter;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoginRequest = requestURI.endsWith("/login.jsp") || requestURI.endsWith("/login");
        boolean isRegisterRequest = requestURI.endsWith("/register.jsp") || requestURI.endsWith("/register");
        boolean isStaticResource = requestURI.contains("/styles");

        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);

        if(isLoginRequest || isRegisterRequest || isStaticResource || isLoggedIn){
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
