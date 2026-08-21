package com.taskjam.repositories;

import com.taskjam.entities.Project;
import com.taskjam.entities.ProjectMembers;
import com.taskjam.mappers.ProjectSupplier;

import java.sql.Connection;
import java.util.List;
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
                project.getName(),project.getDescription(),project.getCreatedBy(),project.getCreatedAt());
    }

    public boolean deleteProjectById(int id){
        String sql = "DELETE FROM projects WHERE id = ?";
        return executeUpdate(sql,id);
    }
}
