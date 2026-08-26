package com.taskjam.service;

import com.taskjam.entity.Project;
import com.taskjam.repository.ProjectRepository;
import com.taskjam.repository.UserRepository;

public class ProjectService {

     private final ProjectRepository projectRepo;
     private final UserRepository userRepo;

    public ProjectService(ProjectRepository projectRepo, UserRepository userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    public int addProject(Project project){
        if(project == null)
            throw new IllegalArgumentException("Project can't be null");
        String projectName = project.getName();
        if(projectName == null || projectName.trim().isEmpty() )
            throw new IllegalArgumentException("Project name cant be empty");
        if(projectRepo.getProjectByName(projectName).isPresent())
            throw new IllegalArgumentException("A project with this name already exists");
        if(userRepo.getUserByName(project.getCreatedBy()).isEmpty())
            throw new IllegalArgumentException("A project cannot be created by a nonexistent user");
        return projectRepo.addProject(project);
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
}
