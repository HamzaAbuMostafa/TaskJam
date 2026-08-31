package com.taskjam.servlet;

import com.taskjam.entity.Project;
import com.taskjam.entity.User;
import com.taskjam.repository.ProjectMembersRepository;
import com.taskjam.repository.ProjectRepository;
import com.taskjam.repository.UserRepository;
import com.taskjam.service.ProjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init(){
        projectService = new ProjectService(new ProjectRepository(), new UserRepository(),
                new ProjectMembersRepository());

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("create-project.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null){
            String error = URLEncoder.encode("Unauthorized Please log in to create a project.", StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/login?error=" + error);
            return;
        }
        int creatorId = (Integer) session.getAttribute("userId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.now();
        try{
            Project project = new Project(name, description, creatorId, dateTime);
            int newProjectId = projectService.createProjectAndAssignOwner(project);
            String message = URLEncoder.encode("Project created Successfully", StandardCharsets.UTF_8);
            response.sendRedirect("project-details.jsp?id=" + newProjectId + "&message=" + message);
        } catch (Exception e) {
            String error = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect(request.getContextPath() + "/project?error=" + error);
        }
    }
}
