package com.taskjam.repository;

import com.taskjam.entity.Project;
import com.taskjam.mapper.ProjectSupplier;

import java.sql.Connection;
import java.util.Optional;

public class ProjectRepository extends GenericRepository {

    public Optional<Project> getProjectById(int id){
        String sql = "SELECT * FROM projects WHERE id = ?";
        return executeQuery(sql, ProjectSupplier::getProject, id);
    }

    public  Optional<Project> getProjectByName(String name){
        String sql = "SELECT * FROM projects WHERE name = ?";
        return executeQuery(sql, ProjectSupplier::getProject, name);
    }

    public int addProject(Project project){
        String sql = "INSERT INTO projects (name,description,created_by,created_at) VALUES (?,?,?,?)";
        return executeUpdateReturnGeneratedKey(sql,
                project.getName(),project.getDescription(),project.getCreatorId(),project.getCreatedAt());
    }

    public int addProject(Project project, Connection connection){
        String sql = "INSERT INTO projects (name,description,created_by,created_at) VALUES (?,?,?,?)";
        return executeUpdateReturnGeneratedKey(sql,connection,
                project.getName(),project.getDescription(),project.getCreatorId(),project.getCreatedAt());
    }

    public boolean deleteProjectById(int id){
        String sql = "DELETE FROM projects WHERE id = ?";
        return executeUpdate(sql,id);
    }
}
