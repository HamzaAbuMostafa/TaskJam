package com.taskjam.service;

import com.taskjam.entity.Project;
import com.taskjam.entity.ProjectMembers;
import com.taskjam.entity.User;
import com.taskjam.repository.ProjectMembersRepository;
import com.taskjam.repository.ProjectRepository;
import com.taskjam.repository.UserRepository;

import java.util.List;

public class ProjectMembersService {

    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;
    private final ProjectMembersRepository projectMembersRepo;

    public ProjectMembersService(UserRepository userRepo, ProjectRepository projectRepo, ProjectMembersRepository projectMembersRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.projectMembersRepo = projectMembersRepo;
    }

    public boolean assignUserToProject(int projectId, int userId){
        if(projectId < 1 || userId < 1)
            throw new IllegalArgumentException("ID is invalid");
        if(projectRepo.getProjectById(projectId).isEmpty() || userRepo.getUserById(userId).isEmpty())
            throw new IllegalArgumentException("There is no project or user that matches this ID");

        boolean isAlreadyMember = projectMembersRepo.getProjectMembersById(projectId).stream()
                .anyMatch(user -> user.getId() == userId);
        if(isAlreadyMember)
            throw new IllegalArgumentException("User is already assigned to this project");
        ProjectMembers projectMembers = new ProjectMembers(projectId, userId);
        return projectMembersRepo.addProjectMembers(projectMembers);
    }

    public boolean removeUserFromProject(int projectId, int userId, String requesterUsername){
        if(projectId < 1 || userId < 1)
            throw new IllegalArgumentException("ID is invalid");
        Project project = projectRepo.getProjectById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        if(!project.getCreatedBy().equalsIgnoreCase(requesterUsername))
            throw new IllegalArgumentException("Unauthorized: Only the project creator can remove members.");
        return projectMembersRepo.deleteProjectMembers(projectId,userId);
    }

    public List<User> getProjectRoster(int projectId){
        if(projectId < 1)
            throw new IllegalArgumentException("ID is invalid");
        return projectMembersRepo.getProjectMembersById(projectId);
    }
}
