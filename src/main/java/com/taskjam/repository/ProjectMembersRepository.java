package com.taskjam.repository;

import com.taskjam.entity.Project;
import com.taskjam.entity.ProjectMembers;
import com.taskjam.entity.User;
import com.taskjam.mapper.ProjectSupplier;
import com.taskjam.mapper.UserSupplier;

import java.sql.Connection;
import java.util.List;

public class ProjectMembersRepository extends GenericRepository {

    public boolean addProjectMembers(ProjectMembers projectMembers){
        String sql = "INSERT INTO project_members (project_id,user_id) VALUES (?,?)";
        return executeUpdate(sql, projectMembers.getProjectId(),projectMembers.getUserId());
    }

    public boolean addProjectMembers(ProjectMembers projectMembers, Connection connection){
        String sql = "INSERT INTO project_members (project_id,user_id) VALUES (?,?)";
        return executeUpdate(sql, connection,
                projectMembers.getProjectId(),projectMembers.getUserId());
    }

    public boolean deleteProjectMembers(int projectId, int userId){
        String sql = "DELETE FROM project_members WHERE project_id = ? AND user_id = ?";
        return executeUpdate(sql, projectId, userId);
    }

    public List<User> getProjectMembersById (int projectId){
        String sql = "SELECT users.* FROM users JOIN project_members ON users.id = project_members.user_id  WHERE project_id = ?";
        return executeQueryForList(sql, UserSupplier::getUser, projectId);
    }

    public List<Project> getUserProjectsById (int userId){
        String sql = "SELECT projects.* FROM projects JOIN project_members ON projects.id = project_members.project_id WHERE user_id = ?";
        return executeQueryForList(sql, ProjectSupplier::getProject, userId);
    }
}
