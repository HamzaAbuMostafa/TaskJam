package com.taskjam.repositories;

import com.taskjam.entities.ProjectMembers;
import com.taskjam.entities.User;
import com.taskjam.mappers.ProjectMembersSupplier;
import com.taskjam.mappers.ProjectSupplier;
import com.taskjam.mappers.UserSupplier;

import java.util.List;
import java.util.Optional;

public class ProjectMembersRepository extends GenericRepository<ProjectMembers> {

    public boolean addProjectMembers(ProjectMembers projectMembers){
        String sql = "INSERT INTO project_members (project_id,user_id) VALUES (?,?)";
        return executeUpdate(sql,projectMembers.getProjectId(),projectMembers.getUserId());
    }

    public boolean deleteProjectMembers(int projectId, int userId){
        String sql = "DELETE FROM project_members WHERE project_id = ? AND user_id = ?";
        return executeUpdate(sql, projectId, userId);
    }
}
