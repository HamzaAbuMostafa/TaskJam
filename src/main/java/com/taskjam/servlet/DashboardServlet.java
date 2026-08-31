package com.taskjam.servlet;

import com.taskjam.DTO.ProjectDTO;
import com.taskjam.repository.ProjectMembersRepository;
import com.taskjam.repository.ProjectRepository;
import com.taskjam.repository.UserRepository;
import com.taskjam.service.ProjectMembersService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ProjectMembersService projectMembersService;

    @Override
    public void init(){
        projectMembersService = new ProjectMembersService(
                new UserRepository(), new ProjectRepository(), new ProjectMembersRepository());
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("userId") != null){
            int userId = (Integer) session.getAttribute("userId");
            List<ProjectDTO> userProjects = projectMembersService.getUserProjectDashboard(userId);
            request.setAttribute("projectsList", userProjects);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
            if(session != null || session.getAttribute("userId") != null){
                int userId = (Integer) session.getAttribute("userId");
                List<ProjectDTO> userProjects = projectMembersService.getUserProjectDashboard(userId);
                request.setAttribute("projectsList", userProjects);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);


    }
}
