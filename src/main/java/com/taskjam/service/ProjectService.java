package com.taskjam.service;

import com.taskjam.config.DatabaseConnectionManager;
import com.taskjam.entity.Project;
import com.taskjam.entity.ProjectMembers;
import com.taskjam.entity.User;
import com.taskjam.repository.ProjectMembersRepository;
import com.taskjam.repository.ProjectRepository;
import com.taskjam.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ProjectService {

     private final ProjectRepository projectRepo;
     private final UserRepository userRepo;
     private final ProjectMembersRepository projectMembersRepo;

    public ProjectService(ProjectRepository projectRepo, UserRepository userRepo,ProjectMembersRepository projectMembersRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
        this.projectMembersRepo = projectMembersRepo;
    }

    public int createProjectAndAssignOwner(Project project){
        projectValidation(project);
        User owner = userRepo.getUserById(project.getCreatorId()).orElseThrow(
                () -> new IllegalArgumentException("A project cannot be created by a nonexistent user"));
        try(Connection connection = DatabaseConnectionManager.getInstance().getConnection()){
            connection.setAutoCommit(false);
            try {
                int newProjectId = projectRepo.addProject(project, connection);
                if(newProjectId == -1){
                    throw new SQLException("Failed to generate project ID");
                }
                ProjectMembers newMember = new ProjectMembers(newProjectId, owner.getId());
                boolean memberAdded = projectMembersRepo.addProjectMembers(newMember, connection);

                if(!memberAdded){
                    throw new SQLException("Failed to add project member");
                }
                connection.commit();
                return newProjectId;
            } catch (SQLException e){
                connection.rollback();
                throw new RuntimeException("Transaction failed and was rolled back.");
            }
        } catch (SQLException e){
            throw new RuntimeException("Database connection error." + e);
        }
    }

    public Project getProjectDetails(int id){
        if(id < 1)
            throw new IllegalArgumentException("Invalid project ID");
        return projectRepo.getProjectById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public boolean deleteProject(int id) {
        if(id < 1)
            return false;
            return projectRepo.deleteProjectById(id);
    }

    private void projectValidation(Project project){
        if(project == null)
            throw new IllegalArgumentException("Project can't be null");
        String projectName = project.getName();
        if(projectName == null || projectName.trim().isEmpty() )
            throw new IllegalArgumentException("Project name cant be empty");
        if(projectRepo.getProjectByName(projectName).isPresent())
            throw new IllegalArgumentException("A project with this name already exists");
        if(userRepo.getUserById(project.getCreatorId()).isEmpty())
            throw new IllegalArgumentException("A project cannot be created by a nonexistent user");
    }
}
