package com.taskjam.servlet;

import com.taskjam.repository.UserRepository;
import com.taskjam.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(){
        userService = new UserService(new UserRepository());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String rawPassword = request.getParameter("password");
        try {
            userService.registerUser(userName, email, rawPassword);
            String successMessage = URLEncoder.encode("Account created successfully! Please log in.", StandardCharsets.UTF_8);
            response.sendRedirect("login.jsp?success=" + successMessage);
        } catch (IllegalArgumentException e){
            String errorMessage = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("register.jsp?error=" + errorMessage);        }
    }
}
