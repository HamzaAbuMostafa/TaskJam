package com.taskjam.mappers;

import com.taskjam.entities.ProjectMembers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMembersSupplier {
    public static ProjectMembers getProjectMembers(ResultSet rs) throws SQLException{
        return new ProjectMembers(
                rs.getInt("project_id"),
                rs.getInt("user_id")
        );
    }
}
