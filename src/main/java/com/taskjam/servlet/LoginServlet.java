package com.taskjam.servlet;

import com.taskjam.entity.User;
import com.taskjam.repository.UserRepository;
import com.taskjam.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(){
        userService = new UserService(new UserRepository());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Optional<User> user = userService.authenticateUser(email, password);
        if(user.isPresent()){
            int id = user.get().getId();
            HttpSession session = request.getSession();
            session.setAttribute("userId",id);
            response.sendRedirect("dashboard");
        } else {
            String errorMessage = URLEncoder.encode("Incorrect email or password.", StandardCharsets.UTF_8);
            response.sendRedirect("login?error=" + errorMessage);
        }
    }
}
