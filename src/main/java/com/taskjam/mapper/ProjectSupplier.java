package com.taskjam.mapper;

import com.taskjam.entity.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProjectSupplier {
    public static Project getProject(ResultSet rs) throws SQLException {
        return new Project(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("created_by"),
                rs.getObject("created_at", LocalDateTime.class)
        );
    }
}
